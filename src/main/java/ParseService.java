import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ParseService {
    public void parse(String inputFilename,String outputFilename, FileTypes inputFileType, FileTypes outputFiletype) throws IOException, ParseException, ParserConfigurationException, SAXException, JAXBException;
}
