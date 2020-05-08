package ru.parser.token;

import ru.parser.Operand;

import java.util.Objects;

public class IntToken implements Operand
{
    private final int val;

    public IntToken(int val)
    {
        this.val = val;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntToken intToken = (IntToken) o;
        return val == intToken.val;
    }

    @Override
    public int toInt()
    {
        return val;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(val);
    }

    @Override
    public String toString()
    {
        return "IntToken{" +
                "val=" + val +
                '}';
    }
}
