package by.pp_project.Implementations;

import by.pp_project.JSONImplementation.SimpleJSONParser;
import by.pp_project.Model.FileType;
import by.pp_project.PlainTextImplementation.*;
import by.pp_project.Services.ParseService;
import by.pp_project.XMLImplementation.XMLDOMParser;
import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;
import java.util.List;

public class ParseServiceImplementation implements ParseService {
    private XMLDOMParser xmldomParser = new XMLDOMParser();

    private SimpleJSONParser simpleJSONParser = new SimpleJSONParser();

    private PlainTextParser plainTextParser = new PlainTextParser();

    @Override
    public byte[] parse(FileType fileType, String outputFiletype) throws Exception {
        List<Expression> expressions;
        List<Result> results = new ArrayList<>();
        byte[] array = fileType.getData();
        switch (FilenameUtils.getExtension(fileType.filename)) {
            case "txt":
                expressions = plainTextParser.parse(array);
                break;
            case "json":
                expressions = simpleJSONParser.parse(array);
                break;
            case "xml":
                expressions = xmldomParser.parse(array);
                break;
            case default:
                throw new IllegalArgumentException("Incorrect file format");
        }
        for (Expression e : expressions) {
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(e.getContent());
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            results.add(new Result(e.getName(), Lexeme.expr(lexemeBuffer)));
        }
        switch (outputFiletype) {
            case "plain":
                return plainTextParser.encode(results);
            case "json":
                return simpleJSONParser.encode(results);
            case "xml":
                Results result = new Results();
                result.setResults(results);
                return xmldomParser.encode(result);
            case default:
                return new byte[0];
        }

    }

}
