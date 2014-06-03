package main.expression.parser.exceptions;

import java.text.ParseException;

public class UnsupportedElementException extends ParseException
{
    private static final long serialVersionUID = 1L;

    public UnsupportedElementException(String name, int errorOffset)
    {
        super(name, errorOffset);
    }
}
