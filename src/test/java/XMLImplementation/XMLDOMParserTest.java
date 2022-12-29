package XMLImplementation;

import PlainTextImplementation.Expression;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLDOMParserTest {

    @Test
    public void parse() throws IOException, SAXException, ParserConfigurationException {
        XMLDOMParser xmlParser = new XMLDOMParser();
        List<Expression> expressions = xmlParser.parse("Assets/lexemeXML.xml");
        List<Expression> expectations = new ArrayList<>();
        expectations.add(new Expression("first","7+7"));
        expectations.add(new Expression("second","8*2-(-7)"));
        expectations.add(new Expression("third","8---1"));

        Assert.assertEquals(expressions,expectations);

    }
}