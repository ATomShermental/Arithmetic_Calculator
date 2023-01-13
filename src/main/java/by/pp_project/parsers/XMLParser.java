package by.pp_project.parsers;

import by.pp_project.PlainTextImplementation.Expression;
import by.pp_project.PlainTextImplementation.Result;
import by.pp_project.PlainTextImplementation.Results;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements Parser{
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;
    List<Expression> expressions;
    NodeList nodeList;
    @Override
    public List<Expression> parse(byte[] array) throws IOException, ParserConfigurationException, SAXException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(byteArrayInputStream);
        byteArrayInputStream.close();
        expressions = new ArrayList<>();
        nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                String content = element.getElementsByTagName("content").item(0).getChildNodes().item(0).getNodeValue();

                expressions.add(new Expression(name, content));
            }
        }


        return expressions;
    }

    @Override
    public byte[] encode(List<Result> results) throws IOException, JAXBException {
        Results results_m = new Results();
        results_m.setResults(results);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Results.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(results_m, byteArrayOutputStream);
        byte[] array = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return array;
    }
}
