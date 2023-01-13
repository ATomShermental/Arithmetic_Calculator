package by.pp_project.factories;

import by.pp_project.parsers.Parser;
import by.pp_project.parsers.XMLParser;

public class XMLFactory implements AbstractFactory{


    @Override
    public Parser createParser() {
        return new XMLParser();
    }
}
