
import PlainTextRealisation.Lexeme;
import PlainTextRealisation.LexemeBuffer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        while(scanner.hasNextLine()){
            String buffer = scanner.nextLine();
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(buffer);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            writer.write(Lexeme.expr(lexemeBuffer) + "\n");
        }
        writer.close();



    }
}