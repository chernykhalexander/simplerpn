package ru.parser;

import org.junit.Test;
import ru.parser.token.IntToken;
import ru.parser.token.VariableToken;
import ru.rpn.Executor;
import ru.rpn.RPNConverter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExecutorTest
{
    @Test
    public void shouldExecuteExpression() throws IOException
    {
        String input = "(1+2)*4+3";
        Operand result = new Executor().execute(new RPNConverter().convert(new Parser().parse(input)), Collections.emptyMap());
        assertThat(result, is(instanceOf(IntToken.class)));
        assertThat(result.toInt(), is(15));
    }

    @Test
    public void shouldExecuteExpressionWithVariable() throws Exception
    {
        String input = "(a+b)*f+d";
        Map<VariableToken, Operand> table = new HashMap<>();
        table.put(new VariableToken("a"), new IntToken(-1));
        table.put(new VariableToken("b"), new IntToken(2));
        table.put(new VariableToken("f"), new IntToken(3));
        table.put(new VariableToken("d"), new IntToken(4));

        Operand result = new Executor().execute(new RPNConverter().convert(new Parser().parse(input)), table);
        assertThat(result, is(instanceOf(IntToken.class)));
        assertThat(result.toInt(), is(7));
    }

    @Test
    public void shouldExecuteDeleteOperationProperly() throws IOException
    {
        String input = "4/2";
        Operand result = new Executor().execute(new RPNConverter().convert(new Parser().parse(input)), Collections.emptyMap());
        assertThat(result.toInt(), is(2));
    }

    @Test
    public void shouldExecuteDeleteOperationProperlyInComplexExperession() throws IOException
    {
        String input = "1+3*(2-10)/4";
        Operand result = new Executor().execute(new RPNConverter().convert(new Parser().parse(input)), Collections.emptyMap());
        assertThat(result.toInt(), is(-5));
    }
}
