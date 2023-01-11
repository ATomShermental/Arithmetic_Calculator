package XMLImplementation;

import PlainTextImplementation.Expression;

import PlainTextImplementation.Results;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


import org.w3c.dom.Document;

public class XMLDOMParser {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;
    List<Expression> expressions;
    NodeList nodeList;
    FileWriter writer;

    public List<Expression> parse(byte[] array) throws ParserConfigurationException, IOException, SAXException {
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

    public byte[] encode(Results results) throws JAXBException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Results.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(results, byteArrayOutputStream);
        byte[] array = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return array;
    }
}
