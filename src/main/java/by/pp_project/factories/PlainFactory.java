package by.pp_project.factories;

import by.pp_project.parsers.Parser;
import by.pp_project.parsers.PlainParser;

public class PlainFactory implements AbstractFactory{

    @Override
    public Parser createParser() {
        return new PlainParser();
    }
}
