package by.pp_project.Implementations;

import by.pp_project.Model.FileType;
import by.pp_project.PlainTextImplementation.*;
import by.pp_project.Services.ParseService;
import by.pp_project.factories.AbstractFactory;
import by.pp_project.factories.JSONFactory;
import by.pp_project.factories.PlainFactory;
import by.pp_project.factories.XMLFactory;
import by.pp_project.parsers.Parser;
import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;
import java.util.List;

public class ParseServiceImplementation implements ParseService {

    AbstractFactory jsonFactory = new JSONFactory();

    AbstractFactory xmlFactory = new XMLFactory();

    AbstractFactory plainFactory = new PlainFactory();

    Parser xmldomParser = xmlFactory.createParser();

    Parser simpleJSONParser = jsonFactory.createParser();

    Parser plainTextParser = plainFactory.createParser();

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
                return xmldomParser.encode(results);
            case default:
                return new byte[0];
        }

    }

}
