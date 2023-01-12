package main.java;


import controller.MainPageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackageClasses = MainPageController.class)

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}