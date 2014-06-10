package exceptions;

public class CalculationException extends Exception
{
    private static final long serialVersionUID = 1L;

    public CalculationException(String name)
    {
        super(name);
    }
}
