package ru.parser.operator;

import ru.parser.token.IntToken;
import ru.parser.Operand;
import ru.parser.Operator;

public class Multiply implements Operator
{
    public static final Multiply instance = new Multiply();

    @Override
    public int getPriority()
    {
        return 3;
    }
    public Operand execute(Operand op1, Operand op2)
    {
        return new IntToken(op1.toInt() * op2.toInt());
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Multiply;
    }
}
