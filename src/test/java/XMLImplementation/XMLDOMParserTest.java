package XMLImplementation;

import PlainTextImplementation.Expression;
import PlainTextImplementation.Result;
import PlainTextImplementation.Results;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLDOMParserTest {
    XMLDOMParser xmldomParser = new XMLDOMParser();
    @Test
    void parse() throws ParserConfigurationException, IOException, SAXException {
        String xml = "<Expressions>\n" +
                "  <Expression name='expr1'>\n" +
                "    <content>8---1</content>\n" +
                "  </Expression>\n" +
                "  <Expression name='expr2'>\n" +
                "    <content>7+++7</content>\n" +
                "  </Expression>\n" +
                "</Expressions>";
        byte[] array = xml.getBytes();
        List<Expression> expressions = xmldomParser.parse(array);
        assertEquals(2, expressions.size());
        assertEquals("expr1", expressions.get(0).getName());
        assertEquals("8---1", expressions.get(0).getContent());
        assertEquals("expr2", expressions.get(1).getName());
        assertEquals("7+++7", expressions.get(1).getContent());
    }

    @Test
    void encode() throws JAXBException, IOException {
        Results results = new Results();
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("expr1", 4));
        resultList.add(new Result("expr2", 12));
        results.setResults(resultList);

        byte[] encoded = xmldomParser.encode(results);
        String decoded = new String(encoded);
        assertTrue(decoded.contains("<Result>"));
        assertTrue(decoded.contains("<name>expr1</name>"));
        assertTrue(decoded.contains("<result>4</result>"));
        assertTrue(decoded.contains("<name>expr2</name>"));
        assertTrue(decoded.contains("<result>12</result>"));
    }
}