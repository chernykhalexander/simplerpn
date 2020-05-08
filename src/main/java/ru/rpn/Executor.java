package ru.rpn;

import ru.parser.AritySize;
import ru.parser.Operand;
import ru.parser.Operator;
import ru.parser.Tokenable;
import ru.parser.token.IntToken;
import ru.parser.token.VariableToken;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Executor
{
    public Operand execute(List<Tokenable> rpn, Map<VariableToken, Operand> table)
    {
        LinkedList<Operand> stack = new LinkedList<>();
        for (Tokenable token : rpn)
        {
            if (token instanceof Operand)
            {
                stack.push(((Operand) token));
            }
            if (token instanceof VariableToken)
            {
                stack.push(table.get(token));
            }

            if (token instanceof Operator)
            {
                Operator op = (Operator) token;
                AritySize arity = op.getArity();
                isAritySupported(arity);
                if (arity == AritySize.BINARY)
                {
                    Operand op2 = stack.pop();
                    isOperandSupported(op2);
                    Operand op1 = stack.pop();
                    isOperandSupported(op1);
                    stack.push(op.execute(op1, op2));
                }
            }
        }
        return stack.pop();
    }

    private void isAritySupported(AritySize size)
    {
        if (size != AritySize.BINARY)
        {
            throw new IllegalStateException("Arity size not supported");
        }

    }

    private void isOperandSupported(Operand operand)
    {
        if (!(operand instanceof IntToken))
        {
            throw new IllegalStateException("Operand type not supported");
        }
    }
}
