package JSONImplementation;


import PlainTextImplementation.Expression;

import PlainTextImplementation.Result;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class SimpleJSONParser {
   private JSONArray array;
   private JSONParser parser;
   FileWriter writer;

    public List<Expression> parse(String input) throws IOException, ParseException {
        parser = new JSONParser();
        array = (JSONArray) parser.parse(new FileReader(input));
        List<Expression> expressions = new ArrayList<>();
        for(Object obj : array){
            JSONObject sample = (JSONObject) obj;
            expressions.add(new Expression((String) sample.get("name"),(String) sample.get("content")));
        }

        return  expressions;
    }

    public void encode(String output,List<Result> results) throws IOException {
        File outputFile = new File(output);
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
        FileWriter fileWriter = new FileWriter(output);
        fileWriter.write(json);
        fileWriter.close();


    }
}
