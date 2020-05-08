package ru.parser;

import org.junit.Test;
import ru.parser.operator.Minus;
import ru.parser.operator.Multiply;
import ru.parser.operator.Plus;
import ru.parser.token.Bracket;
import ru.parser.token.IntToken;
import ru.parser.token.VariableToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParserTest
{
    @Test
    public void simpleParseToTokens() throws IOException
    {
        String input = "-(1 + 2)*4 +3";

        List<Tokenable> tokens = new ArrayList<>();
        tokens.add(Minus.instance);
        tokens.add(Bracket.LEFT_BRACKET);
        tokens.add(new IntToken(1));
        tokens.add(Plus.instance);
        tokens.add(new IntToken(2));
        tokens.add(Bracket.RIGHT_BRACKET);
        tokens.add(Multiply.instance);
        tokens.add(new IntToken(4));
        tokens.add(Plus.instance);
        tokens.add(new IntToken(3));
        assertThat(new Parser().parse(input), is(tokens));
    }

    @Test
    public void shouldParseVariables() throws IOException
    {
        String input = "a+b*c";
        List<Tokenable> tokens = new ArrayList<>();
        tokens.add(new VariableToken("a"));
        tokens.add(Plus.instance);
        tokens.add(new VariableToken("b"));
        tokens.add(Multiply.instance);
        tokens.add(new VariableToken("c"));
        assertThat(new Parser().parse(input), is(tokens));
    }
}
