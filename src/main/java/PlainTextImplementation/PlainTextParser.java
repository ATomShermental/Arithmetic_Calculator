package PlainTextImplementation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlainTextParser {


   public List<Expression> parse(byte[] array) throws IOException {
       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        Scanner scanner = new Scanner(byteArrayInputStream);
        byteArrayInputStream.close();
        List<Expression> expressions = new ArrayList<>();
        while(scanner.hasNext()){
            expressions.add(new Expression(scanner.next(),scanner.nextLine()));
        }
        return expressions;
    }

    public byte[] encode(List<Result> results)  {

        StringBuilder sb = new StringBuilder();
        for(Result e : results){
            sb.append(e.getName());
            sb.append(" ");
            sb.append(e.getResult());
            sb.append(System.lineSeparator());
        }
          return  sb.toString().getBytes();
    }
}
