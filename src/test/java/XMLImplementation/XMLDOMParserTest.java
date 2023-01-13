package XMLImplementation;

import by.pp_project.PlainTextImplementation.Expression;
import by.pp_project.PlainTextImplementation.Result;
import by.pp_project.factories.AbstractFactory;
import by.pp_project.factories.XMLFactory;
import by.pp_project.parsers.Parser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLDOMParserTest {
    AbstractFactory abstractFactory = new XMLFactory();

    Parser xmldomParser = abstractFactory.createParser();
    @Test
    void parse() throws ParserConfigurationException, IOException, SAXException, ParseException {
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
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("expr1", 4));
        resultList.add(new Result("expr2", 12));
        byte[] encoded = xmldomParser.encode(resultList);
        String decoded = new String(encoded);
        assertTrue(decoded.contains("<Result>"));
        assertTrue(decoded.contains("<name>expr1</name>"));
        assertTrue(decoded.contains("<result>4</result>"));
        assertTrue(decoded.contains("<name>expr2</name>"));
        assertTrue(decoded.contains("<result>12</result>"));
    }
}