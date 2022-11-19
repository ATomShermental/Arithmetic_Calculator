package main.java;

import PlainTextRealisation.Lexeme;
import PlainTextRealisation.LexemeBuffer;
import des.DES;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("Assets/input.txt");
        String outputStr = "Assets/output.txt";
        Scanner scanner = new Scanner(input);
        FileWriter writer = new FileWriter(outputStr);
        while(scanner.hasNextLine()){
            String buffer = scanner.nextLine();
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(buffer);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            writer.write(Lexeme.expr(lexemeBuffer) + "\n");
        }
        writer.close();
        DES.encrypt(input);
        DES.decrypt(new File( "Assets/encrypted.txt"));



    }
}