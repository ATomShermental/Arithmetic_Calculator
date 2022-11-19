package JSONRealisation;

import XMLRealisation.XMLDOMParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SimpleJSONParserTest {

    @Test
    public void parse() throws IOException, ParseException {

        SimpleJSONParser jsonParser = new SimpleJSONParser();
        jsonParser.parse();
        Scanner scanner = new Scanner(new File("Assets/lexemeJSONDecoded.txt"));
        List<Integer> exp = Arrays.asList(14,23,7);
        List<Integer> result = new ArrayList<>();
        while(scanner.hasNext()){
            String buffer = scanner.next();
            result.add(scanner.nextInt());

        }
        Assert.assertEquals(exp, result);
    }
}