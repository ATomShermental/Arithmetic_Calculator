import PlainTextImplementation.Lexeme;
import PlainTextImplementation.LexemeBuffer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlainTextTest {

    @Test
    public void testPlainTextRealisation(){
        String[] expression = {"7+8", "8*2-(-7)","8*-(-5+2)"};
        int[] answers = new int[expression.length];
        for(int i = 0; i < expression.length; i++){
            List<Lexeme> lexemes = Lexeme.lexemeAnalyze(expression[i]);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            answers[i] = Lexeme.expr(lexemeBuffer);
        }
        Assert.assertArrayEquals(new int[]{15, 23, 24},answers);
    }


}
