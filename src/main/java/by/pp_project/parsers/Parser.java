package by.pp_project.parsers;

import by.pp_project.PlainTextImplementation.Expression;
import by.pp_project.PlainTextImplementation.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Expression> parse(byte[] array) throws IOException, ParseException, ParserConfigurationException, SAXException;

    byte[] encode(List<Result> results) throws IOException, JAXBException;
}
