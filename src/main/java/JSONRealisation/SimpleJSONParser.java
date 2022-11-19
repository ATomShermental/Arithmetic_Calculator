package JSONRealisation;


import PlainTextRealisation.Expression;
import PlainTextRealisation.Lexeme;
import PlainTextRealisation.LexemeBuffer;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class SimpleJSONParser {
   private JSONArray array;
   private JSONParser parser;
   FileWriter writer;

    void parse() throws IOException, ParseException {
        parser = new JSONParser();
        array = (JSONArray) parser.parse(new FileReader("lexemeJSON.json"));
        List<Expression> expressions = new ArrayList<>();
        for(Object obj : array){
            JSONObject sample = (JSONObject) obj;
            expressions.add(new Expression((String) sample.get("name"),(String) sample.get("content")));
        }

        writer = new FileWriter("lexemeJSONDecoded.txt");
        for(var expr : expressions){
            String buffer = expr.getContent();
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(buffer);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            writer.write(expr.getName() + " " + Lexeme.expr(lexemeBuffer) + "\n");
        }
        writer.close();
    }
}
