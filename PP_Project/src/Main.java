
import PlainTextRealisation.Lexeme;
import PlainTextRealisation.LexemeBuffer;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        while(scanner.hasNextLine()){
            String buffer = scanner.nextLine();
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(buffer);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            System.out.println(Lexeme.expr(lexemeBuffer));
        }



    }
}