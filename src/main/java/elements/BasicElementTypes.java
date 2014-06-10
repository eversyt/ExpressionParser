package elements;

import operations.IOperation;
import operations.Operations;
import parser.AbstractSupportedMap;
import parser.ExpressionElement;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BasicElementTypes implements IElementType
{
    NUMBER(99)
            {
                @Override
                public ExpressionElement check(String expression, int position, AbstractSupportedMap<IOperation> operations)
                {
                    return typeCheck(expression, position, this, null, getPriority(), NUMBER_REGEX);
                }
            },

    OPERATION(0)
            {
                @Override
                public ExpressionElement check(String expression, int position, AbstractSupportedMap<IOperation> operations)
                {
                    ExpressionElement result;
                    String sign;
                    for (Entry<String, IOperation> entry : operations.getMap().entrySet())
                    {
                        sign = entry.getKey();
                        if (sign.replaceAll(Operations.LEGAL_SYMBOLS_REGEX, "").length() == 0)
                        {
                            sign = "\\" + sign;
                        }

                        result = typeCheck(expression
                                , position
                                , this
                                , entry.getValue()
                                , entry.getValue().getPriority()
                                , "(" + sign + ")");

                        if (result != null)
                        {
                            return result;
                        }
                    }
                    return null;
                }
            },
    OPEN_BRACKET(100)
            {
                @Override
                public ExpressionElement check(String expression, int position, AbstractSupportedMap<IOperation> operations)
                {
                    return typeCheck(expression, position, this, null, getPriority(), OPEN_BRACKET_REGEX);
                }
            },

    CLOSED_BRACKET(100)
            {
                @Override
                public ExpressionElement check(String expression, int position, AbstractSupportedMap<IOperation> operations)
                {
                    return typeCheck(expression, position, this, null, getPriority(), CLOSE_BRACKET_REGEX);
                }
            };

    public static final String AT_THE_BEGINNING_REGEX = "^";
    public static final String SPACES_REGEX = "(\\s*)";
    public static final String CLOSE_BRACKET_REGEX = "(\\))";
    public static final String OPEN_BRACKET_REGEX = "(\\()";
    public static final String NUMBER_REGEX = "([0-9]*[.]?[0-9]+)";

    private int priority;

    private BasicElementTypes(int priority)
    {
        this.priority = priority;
    }

    public int getPriority()
    {
        return priority;
    }

    @Override
    public String[] getNames()
    {
        return new String[] { this.toString() };
    }

    public static ExpressionElement typeCheck(String expression, int position
            , IElementType type, IOperation operation, int priority, String elementPattern)
    {
        Pattern pattern = Pattern.compile(AT_THE_BEGINNING_REGEX + SPACES_REGEX + elementPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression.substring(position));
        if (!matcher.find())
        {
            return null;
        }
        String result = matcher.group();
        return new ExpressionElement.ExpressionElementBuilder()
                .withType(type)
                .withValue(result.trim())
                .withPriority(priority)
                .withLengthWithSpace(result.length())
                .withOperation(operation)
                .build();
    }
}

