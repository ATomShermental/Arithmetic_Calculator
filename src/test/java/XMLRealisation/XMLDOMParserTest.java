package XMLRealisation;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class XMLDOMParserTest {

    @Test
    public void parse() throws IOException, SAXException, ParserConfigurationException {
        XMLDOMParser xmlParser = new XMLDOMParser();
        xmlParser.parse();
        Scanner scanner = new Scanner(new File("Assets/lexemeXMLDecoded.txt"));
        List<Integer> exp = Arrays.asList(14,23,7);
        List<Integer> result = new ArrayList<>();
        while(scanner.hasNext()){
            String buffer = scanner.next();
            result.add(scanner.nextInt());

    }
        Assert.assertEquals(exp, result);


    }
}