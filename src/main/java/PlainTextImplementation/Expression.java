package PlainTextImplementation;



public class Expression {
    private  String name;
    private  String content;


    public Expression(){}
    public Expression(String name, String content){
        this.name = name;
        this.content = content;
    }
    @Override
    public boolean equals(Object o){
        Expression expression = (Expression) o;
        if(this.name.equals(expression.name) && this.content.equals(expression.content)){
            return true;
        }
        return false;
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
