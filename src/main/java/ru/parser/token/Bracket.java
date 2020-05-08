package ru.parser.token;

import ru.parser.Priority;

public enum Bracket implements Priority
{
    LEFT_BRACKET
            {
                @Override
                public int getPriority()
                {
                    return 1;
                }
            },
    RIGHT_BRACKET
            {
                @Override
                public int getPriority()
                {
                    return 1;
                }
            }
}
