package PlainTextImplementation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlainTextParser {


   public List<Expression> parse(String input) throws IOException {
        Scanner scanner = new Scanner(new File(input));
        List<Expression> expressions = new ArrayList<>();
        while(scanner.hasNext()){
            expressions.add(new Expression(scanner.next(),scanner.nextLine()));
        }
        return expressions;
    }

    public void encode(String output,List<Result> results) throws IOException {
        FileWriter fileWriter = new FileWriter(output);
        for(var e : results){
            StringBuilder sb = new StringBuilder();
            sb.append(e.getName());
            sb.append(" ");
            sb.append(e.getResult());
            fileWriter.write(sb.toString() + '\n');
        }
        fileWriter.close();

    }
}
