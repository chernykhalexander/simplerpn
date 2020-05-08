package ru.parser;

import org.junit.Test;
import ru.parser.operator.Multiply;
import ru.parser.operator.Plus;
import ru.parser.token.IntToken;
import ru.parser.token.VariableToken;
import ru.rpn.RPNConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RPNConverterTest
{
    @Test
    public void basicRPNConverterTest() throws IOException
    {
        String input = "(1+2)*4+3";
        List<Tokenable> tokens = new Parser().parse(input);

        List<Tokenable> expected = new ArrayList<>();
        expected.add(new IntToken(1));
        expected.add(new IntToken(2));
        expected.add(Plus.instance);
        expected.add(new IntToken(4));
        expected.add(Multiply.instance);
        expected.add(new IntToken(3));
        expected.add(Plus.instance);

        assertThat(new RPNConverter().convert(tokens), is(expected));
    }

    @Test
    public void shouldConvertVariables() throws IOException
    {
        String input = "a+1";
        List<Tokenable> tokens = new Parser().parse(input);

        List<Tokenable> expected = new ArrayList<>();
        expected.add(new VariableToken("a"));
        expected.add(new IntToken(1));
        expected.add(Plus.instance);
        assertThat(new RPNConverter().convert(tokens), is(expected));
    }

    @Test
    public void shouldThrowExceptionWhenNoMatchingRightBracket() throws IOException
    {
        String input = "(1+2 *4 + 3";
        List<Tokenable> tokens = new Parser().parse(input);

        try
        {
            System.out.println(new RPNConverter().convert(tokens));
            fail("Should not be here");
        }
        catch (IllegalStateException e)
        {
           //ok
        }
    }
}
