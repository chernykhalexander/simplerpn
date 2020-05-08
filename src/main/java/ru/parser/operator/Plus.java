package ru.parser.operator;

import ru.parser.token.IntToken;
import ru.parser.Operand;
import ru.parser.Operator;

public class Plus implements Operator
{
    public static final Plus instance = new Plus();

    @Override
    public int getPriority()
    {
        return 2;
    }

    public Operand execute(Operand op1, Operand op2)
    {
        return new IntToken(op1.toInt() + op2.toInt());
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Plus;
    }
}
