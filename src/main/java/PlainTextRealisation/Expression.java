package PlainTextRealisation;

public class Expression {
    private  String name;
    private  String content;



    public Expression(String name, String content){
        this.name = name;
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return "<" + name + ", " + content +">";
    }
}
