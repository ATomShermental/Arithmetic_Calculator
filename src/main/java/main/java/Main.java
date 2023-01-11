package main.java;

import PlainTextImplementation.*;
import XMLImplementation.XMLDOMParser;
import controller.MainPageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
@SpringBootApplication
@ComponentScan(basePackageClasses = MainPageController.class)

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}