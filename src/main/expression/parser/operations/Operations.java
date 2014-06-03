package main.expression.parser.operations;

import main.expression.parser.parser.AbstractSupportedMap;

public class Operations extends AbstractSupportedMap<IOperation>
{
    public static final String LEGAL_CHARACTERS_REGEX = "[a-zA-Z]";
    public static final String LEGAL_SYMBOLS_REGEX = "[!-'*-/:-@^-`|~�]";

    public Operations()
    {
        addBasicOperations();
    }

    private void addBasicOperations()
    {
        for (BasicOperations operation : BasicOperations.values())
        {
            addItem(operation);
        }
    }

    @Override
    public boolean addItem(IOperation customItem)
    {
        if (customItem == null)
        {
            return false;
        }

        for (String sign : customItem.getNames())
        {
            if (!validate(sign))
            {
                return false;
            }
        }

        return super.addItem(customItem);
    }

    /**
     * Check signs of operation. Return true if sign is symbol
     * (one from set "*+=:?!~#{}&%$��^`';:-_<>/|"@��") or a word
     * from Latin without spaces
     * 
     * Examples:
     * !, mod, sin are legal
     * &&&, 'an operation' are not supported
     */
    private boolean validate(String sign)
    {
        if ((sign.replaceAll(LEGAL_SYMBOLS_REGEX, "").length() == 0)
                && (sign.length() == 1))
        {
            return true;
        }
        if (sign.replaceAll(LEGAL_CHARACTERS_REGEX, "").length() == 0)
        {
            return true;
        }
        return false;
    }
}
