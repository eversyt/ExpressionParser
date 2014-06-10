package parser;

import elements.IElementType;
import operations.IOperation;

public class ExpressionElement
{
    private IElementType type;
    private int priority;
    private String value;
    private int lengthWithSpace;
    private IOperation operation;

    public IElementType getType()
    {
        return type;
    }

    public int getPriority()
    {
        return priority;
    }

    public String getValue()
    {
        return value;
    }

    public int getLengthWithSpace()
    {
        return lengthWithSpace;
    }

    public IOperation getOperation()
    {
        return operation;
    }

    public static class ExpressionElementBuilder
    {
        private ExpressionElement element = new ExpressionElement();

        public ExpressionElementBuilder withType(IElementType type)
        {
            element.type = type;
            return this;
        }

        public ExpressionElementBuilder withPriority(int priority)
        {
            element.priority = priority;
            return this;
        }

        public ExpressionElementBuilder withValue(String value)
        {
            element.value = value;
            return this;
        }

        public ExpressionElementBuilder withLengthWithSpace(int lengthWithSpace)
        {
            element.lengthWithSpace = lengthWithSpace;
            return this;
        }

        public ExpressionElementBuilder withOperation(IOperation operation)
        {
            element.operation = operation;
            return this;
        }

        public ExpressionElement build()
        {
            ExpressionElement result = element;
            element = null;
            return result;
        }
    }
}

