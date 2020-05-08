package ru.parser;

public interface Arity
{
    default AritySize getArity(){
        return AritySize.BINARY;
    }
}
