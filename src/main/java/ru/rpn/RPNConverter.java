package ru.rpn;

import ru.parser.*;
import ru.parser.token.Bracket;
import ru.parser.token.IntToken;
import ru.parser.token.VariableToken;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RPNConverter
{
    public List<Tokenable> convert(List<Tokenable> tokens)
    {
        List<Tokenable> rpn = new ArrayList<>();
        LinkedList<Priority> stack = new LinkedList<>();
        for (Tokenable token : tokens)
        {
            if (token instanceof IntToken)
            {
                rpn.add(token);
            }
            if (token instanceof VariableToken)
            {
                rpn.add(token);
            }
            if (token instanceof Operator)
            {
                Operator operator = ((Operator) token);
                while (!stack.isEmpty() && operator.getPriority() <= stack.peek().getPriority())
                {
                    rpn.add(stack.pop());
                }
                if (stack.isEmpty() || operator.getPriority() > stack.peek().getPriority())
                {
                    stack.push(operator);
                }
            }
            if (token instanceof Bracket)
            {
                Bracket bracket = ((Bracket) token);
                if (bracket == Bracket.LEFT_BRACKET)
                {
                    stack.push(bracket);
                }
                if (bracket == Bracket.RIGHT_BRACKET)
                {
                    Priority p = null;
                    while (!stack.isEmpty() && (p = stack.pop()).getPriority() != Bracket.LEFT_BRACKET.getPriority())
                    {
                        rpn.add(p);
                    }
                    if (stack.isEmpty() && p!=null && p.getPriority() != Bracket.LEFT_BRACKET.getPriority())
                    {
                        throw new IllegalStateException("No matching brackets");
                    }
                }
            }
        }

        Tokenable tokenable;
        while (!stack.isEmpty())
        {
            tokenable = stack.pop();
            if (tokenable.equals(Bracket.LEFT_BRACKET))
            {
                throw new IllegalStateException("No matching right bracket");
            }
            rpn.add(tokenable);
        }
        return rpn;
    }
}
