package JSONImplementation;


import PlainTextImplementation.Expression;

import PlainTextImplementation.Result;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SimpleJSONParser {
    private JSONArray array;
    private JSONParser parser;
    FileWriter writer;

    public List<Expression> parse(byte[] byteArray) throws IOException, ParseException {
        parser = new JSONParser();
        array = (JSONArray) parser.parse(new String(byteArray, StandardCharsets.UTF_8));
        List<Expression> expressions = new ArrayList<>();
        for (Object obj : array) {
            JSONObject sample = (JSONObject) obj;
            expressions.add(new Expression((String) sample.get("name"), (String) sample.get("content")));
        }

        return expressions;
    }

    public byte[] encode(List<Result> results) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
        return json.getBytes();
    }
}
