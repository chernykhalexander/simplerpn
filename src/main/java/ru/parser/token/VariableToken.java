package ru.parser.token;

import ru.parser.Tokenable;

import java.util.Objects;

public class VariableToken implements Tokenable
{
    private final String name;

    public VariableToken(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableToken that = (VariableToken) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }

    @Override
    public String toString()
    {
        return "VariableToken{" +
                "name='" + name + '\'' +
                '}';
    }
}
