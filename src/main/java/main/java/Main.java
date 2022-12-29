package main.java;

import PlainTextImplementation.*;
import XMLImplementation.XMLDOMParser;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {
//        File input = new File("Assets/input.txt");
//        String outputStr = "Assets/output.txt";
//        Scanner scanner = new Scanner(input);
//        FileWriter writer = new FileWriter(outputStr);
//        while(scanner.hasNextLine()){
//            String name = scanner.next();
//            String buffer = scanner.nextLine();
//            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(buffer);
//            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
//            writer.write(name + " ");
//            writer.write(Lexeme.expr(lexemeBuffer) + "\n");
//        }
//        writer.close();
//        DES.encrypt(input);
//        DES.decrypt(new File( "Assets/encrypted.txt"));
//
            Results results = new Results();
            results.setResults(new ArrayList<Result>());
            Result result1 = new Result("exp1",6);
            Result result2 = new Result("exp2",5);
            results.getResults().add(result1);
            results.getResults().add(result2);


        XMLDOMParser xmldomParser = new XMLDOMParser();
        xmldomParser.encode("Assets/hello2.xml",results);

    }
}