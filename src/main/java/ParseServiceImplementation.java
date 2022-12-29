import JSONImplementation.SimpleJSONParser;
import PlainTextImplementation.*;
import XMLImplementation.XMLDOMParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseServiceImplementation implements ParseService{
    private XMLDOMParser xmldomParser = new XMLDOMParser();

    private SimpleJSONParser simpleJSONParser = new SimpleJSONParser();

    private PlainTextParser plainTextParser = new PlainTextParser();
    @Override
    public void parse(String inputFilename, String outputFilename, FileTypes inputFileType, FileTypes outputFiletype) throws IOException, ParseException, ParserConfigurationException, SAXException, JAXBException {
        List<Expression> expressions = new ArrayList<>();
        List<Result> results = new ArrayList<>();
        switch (inputFileType){
            case PLAIN_TEXT:
                expressions = plainTextParser.parse(inputFilename);
                break;
            case JSON:
                expressions = simpleJSONParser.parse(inputFilename);
                break;
            case XML:
                expressions = xmldomParser.parse(inputFilename);
                break;
        }
        for(var e : expressions){
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(e.getContent());
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            results.add(new Result(e.getName(),Lexeme.expr(lexemeBuffer)));
        }
        switch (outputFiletype){
            case PLAIN_TEXT:
                plainTextParser.encode(outputFilename,results);
                break;
            case JSON:
                simpleJSONParser.encode(outputFilename,results);
                break;
            case XML:
                Results result = new Results();
                result.setResults(results);
                xmldomParser.encode(outputFilename,result);
        }
    }
}
