package main.expression.parser.exceptions;

import java.text.ParseException;

public class ValidationRuleException extends ParseException
{
    private static final long serialVersionUID = 1L;

    public ValidationRuleException(String name, int errorOffset)
    {
        super(name, errorOffset);
    }
}
