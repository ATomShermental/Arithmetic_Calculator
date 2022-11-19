package XMLRealisation;

import PlainTextRealisation.Expression;
import PlainTextRealisation.Lexeme;
import PlainTextRealisation.LexemeBuffer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import  org.w3c.dom.Document;

public class XMLDOMParser {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;
    List<Expression> expressions;
    NodeList nodeList;
    FileWriter writer;
    void parse() throws ParserConfigurationException, IOException, SAXException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document =  builder.parse(new File("Assets/lexemeXML.xml"));
        expressions = new ArrayList<>();
        nodeList = document.getDocumentElement().getChildNodes();
        for(int i =0; i <nodeList.getLength();i++){
            Node node = nodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                String content = element.getElementsByTagName("content").item(0).getChildNodes().item(0).getNodeValue();

                expressions.add(new Expression(name,content));
            }
        }





        writer = new FileWriter("Assets/lexemeXMLDecoded.txt");
        for(var expr : expressions){
            String buffer = expr.getContent();
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(buffer);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            writer.write(expr.getName() + " " + Lexeme.expr(lexemeBuffer) + "\n");
        }
        writer.close();
    }
}
