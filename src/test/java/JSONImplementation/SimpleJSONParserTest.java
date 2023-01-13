package JSONImplementation;

import by.pp_project.JSONImplementation.SimpleJSONParser;
import by.pp_project.PlainTextImplementation.Expression;
import by.pp_project.PlainTextImplementation.Result;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleJSONParserTest {
    SimpleJSONParser simpleJSONParser = new SimpleJSONParser();
    @Test
    void parse() throws IOException, ParseException {
        byte[] byteArray = (
                "[" +
                "{" +
                "\"name\":\"expr1\"," +
                "\"content\":\"7+7\"" +
                "}," +
                "{" +
                "\"name\":\"expr2\"," +
                "\"content\":\"8*2-(-7)\"" +
                "}," +
                "{" +
                "\"name\":\"expr3\"," +
                "\"content\":\"8---1\"" +
                "}" + "]"
                 ).getBytes(StandardCharsets.UTF_8);
        List<Expression> expressions = simpleJSONParser.parse(byteArray);
        assertEquals(3, expressions.size());
        assertEquals("expr1", expressions.get(0).getName());
        assertEquals("7+7", expressions.get(0).getContent());
        assertEquals("expr2", expressions.get(1).getName());
        assertEquals("8*2-(-7)", expressions.get(1).getContent());
        assertEquals("expr3", expressions.get(2).getName());
        assertEquals("8---1", expressions.get(2).getContent());
    }

    @Test
    void encode() throws IOException {
        List<Result> results = Arrays.asList(
                new Result("result1", 5),
                new Result("result2", -3)
        );
        byte[] encoded = simpleJSONParser.encode(results);
        String json = new String(encoded, StandardCharsets.UTF_8);
        assertEquals("""
                [ {\r
                  "name" : "result1",\r
                  "result" : 5\r
                }, {\r
                  "name" : "result2",\r
                  "result" : -3\r
                } ]""", json);
    }
}