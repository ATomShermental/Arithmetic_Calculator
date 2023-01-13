package by.pp_project.factories;

import by.pp_project.parsers.JSNParser;
import by.pp_project.parsers.Parser;

public class JSONFactory implements AbstractFactory{
    @Override
    public Parser createParser() {
        return new JSNParser();
    }
}
