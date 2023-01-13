package by.pp_project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication

public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
//        File path = new File("Assets/input.txt");
//        FileInputStream fis = new FileInputStream(path);
//        byte[] array = new byte[(int)path.length()];
//        fis.read(array);
//        fis.close4();
//        FileOutputStream fout = new FileOutputStream("Assets/decryptedPlainFile.txt");
//        array = DES.encrypt(array,"12345678".getBytes());
//        fout.write(array);
//        fout.close();
        SpringApplication.run(Main.class,args);
    }
}