package main.java;

import PlainTextImplementation.*;
import XMLImplementation.XMLDOMParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}