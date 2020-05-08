package ru.parser;

import ru.parser.operator.Division;
import ru.parser.operator.Minus;
import ru.parser.operator.Multiply;
import ru.parser.operator.Plus;
import ru.parser.token.Bracket;
import ru.parser.token.IntToken;
import ru.parser.token.VariableToken;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser
{
    public List<Tokenable> parse(String input) throws IOException
    {
        StringReader sr = new StringReader(input);
        List<Tokenable> tokens = new ArrayList<>();

        int symbol;

        while ((symbol = sr.read()) != -1)
        {
            if (isDigit(symbol))
            {
                sr.reset();
                tokens.add(parseNumber(sr));
            }
            if (isMathOperator(symbol))
            {
                sr.reset();
                tokens.add(parseMathOperator(sr));
            }
            if (isBracket(symbol))
            {
                tokens.add(parseBracket(symbol));
            }
            if (isVariable(symbol))
            {
                sr.reset();
                tokens.add(parseVariable(sr));
            }
            sr.mark(0);
        }

        return tokens;
    }

    private boolean isVariable(int symbol)
    {
        return Character.isAlphabetic(symbol);
    }

    private Bracket parseBracket(int symbol)
    {
        if (Arrays.equals("(".toCharArray(), Character.toChars(symbol))) return Bracket.LEFT_BRACKET;
        if (Arrays.equals(")".toCharArray(), Character.toChars(symbol))) return Bracket.RIGHT_BRACKET;
        throw new IllegalArgumentException("Unrecognized symbol " + symbol);
    }

    private boolean isBracket(int symbol)
    {
        return Arrays.equals("(".toCharArray(), Character.toChars(symbol)) ||
                Arrays.equals(")".toCharArray(), Character.toChars(symbol));
    }

    private Operator parseMathOperator(StringReader sr) throws IOException
    {
        int symbol = sr.read();
        sr.mark(0);
        char val = Character.toChars(symbol)[0];
        if (val == '+') return Plus.instance;
        if (val == '-') return Minus.instance;
        if (val == '*') return Multiply.instance;
        if (val == '/') return Division.instance;
        throw new IllegalArgumentException("Unrecognized operator :" + val);
    }

    private boolean isMathOperator(int symbol)
    {
        char val = Character.toChars(symbol)[0];
        return (val == '+' || val == '-' || val == '*' || val == '/');
    }

    private IntToken parseNumber(StringReader sr) throws IOException
    {
        int symbol;
        StringBuilder sb = new StringBuilder();
        while ((symbol = sr.read()) != -1)
        {
            if (isDigit(symbol))
            {
                sb.append(Character.toChars(symbol)[0]);
                sr.mark(0);
            }
            else
            {
                sr.reset();
                break;
            }
        }
        return new IntToken(Integer.parseInt(sb.toString()));
    }

    private VariableToken parseVariable(StringReader sr) throws IOException
    {
        int symbol;
        StringBuilder sb = new StringBuilder();
        while ((symbol = sr.read())!=-1)
        {
            if (isLetter(symbol))
            {
                sb.append(Character.toChars(symbol)[0]);
                sr.mark(0);
            }
            else
            {
                sr.reset();
                break;
            }
        }
        return new VariableToken(sb.toString());
    }

    private boolean isLetter(int symbol){
        return Character.isAlphabetic(symbol);
    }

    private boolean isDigit(int symbol)
    {
        return Character.isDigit(symbol);
    }

}
