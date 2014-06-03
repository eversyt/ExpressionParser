package main.expression.parser.operations;

/**
 * Abstract class for new operations.
 * If the sign of this operation match a symbol of one of basic operations,
 * it's override this basic operation!
 */
public abstract class CustomOperations implements IOperation
{
    private int priority;
    private OperationPrintOption printOption;
    private String[] signs;

    public CustomOperations(int priority, OperationPrintOption printOption, String... signs)
    {
        super();
        this.priority = priority;
        this.printOption = printOption;
        this.signs = signs;
    }

    @Override
    public int getPriority()
    {
        return priority;
    }

    @Override
    public String print()
    {
        switch (printOption)
        {
            case PRINT_WITH_SPACE:
                return " " + signs[0] + " ";
            case PRINT_WITHOUT_SPACE:
            default:
                return signs[0];
        }
    }

    @Override
    public String[] getNames()
    {
        return signs;
    }
}