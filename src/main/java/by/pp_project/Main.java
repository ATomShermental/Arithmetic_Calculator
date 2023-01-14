package by.pp_project;


import by.pp_project.DESImplementation.DES;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


@SpringBootApplication

public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        File path = new File("Assets/inputXML.xml");
        FileInputStream fis = new FileInputStream(path);
        byte[] array = new byte[(int)path.length()];
        fis.read(array);
        fis.close();
        FileOutputStream fout = new FileOutputStream("Assets/encryptedXML.xml");
        array = DES.encrypt(array,"12345678".getBytes());
        fout.write(array);
        fout.close();
        SpringApplication.run(Main.class,args);
    }
}