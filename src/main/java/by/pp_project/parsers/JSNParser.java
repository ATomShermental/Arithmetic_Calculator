package by.pp_project.parsers;

import by.pp_project.PlainTextImplementation.Expression;

import by.pp_project.PlainTextImplementation.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSNParser implements Parser{
    private JSONArray array_m;
    private JSONParser parser;
    @Override

    public List<Expression> parse(byte[] array) throws IOException, ParseException {
        parser = new JSONParser();
        array_m = (JSONArray) parser.parse(new String(array, StandardCharsets.UTF_8));
        List<Expression> expressions = new ArrayList<>();
        for (Object obj : array_m) {
            JSONObject sample = (JSONObject) obj;
            expressions.add(new Expression((String) sample.get("name"), (String) sample.get("content")));
        }

        return expressions;

    }

    @Override
    public byte[] encode(List<Result> results) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
        return json.getBytes();
    }
}
