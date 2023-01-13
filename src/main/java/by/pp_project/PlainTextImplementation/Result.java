package by.pp_project.PlainTextImplementation;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
    private  String name;
    private  int result;


    public Result(){}
    public Result(String name, int result){
        this.name = name;
        this.result = result;
    }
    @Override
    public boolean equals(Object o){
        Result expression = (Result) o;
        if(this.name.equals(expression.name) && this.result == expression.result){
            return true;
        }
        return false;
    }
    public int getResult(){
        return this.result;
    }

    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return "<" + name + ", " + result +">";
    }
}
