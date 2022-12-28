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

    List<Expression> parse(String input) throws IOException, ParseException {
        parser = new JSONParser();
        array = (JSONArray) parser.parse(new FileReader(input));
        List<Expression> expressions = new ArrayList<>();
        for(Object obj : array){
            JSONObject sample = (JSONObject) obj;
            expressions.add(new Expression((String) sample.get("name"),(String) sample.get("content")));
        }

        return  expressions;
    }
}
