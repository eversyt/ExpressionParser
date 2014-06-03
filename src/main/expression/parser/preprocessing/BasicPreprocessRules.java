package main.expression.parser.preprocessing;

import static main.expression.parser.elements.BasicElementTypes.*;

public enum BasicPreprocessRules implements IPreprocessRule
{
    /**
     * Remove spaces before and after expression
     */
    REMOVE_SPACES()
    {
        @Override
        public String preprocess(String expression)
        {
            return expression.trim();
        }
    },

    REPLACE_SQUARE_BRACKET_WITH_ROUND_ONE()
    {
        @Override
        public String preprocess(String expression)
        {
            return expression.replaceAll("\\[", "\\(").replaceAll("\\]", "\\)");
        }
    },

    REPLACE_COMMAS_WITH_DOTS()
    {
        @Override
        public String preprocess(String expression)
        {
            return expression.replaceAll(",", ".");
        }
    },

    /**
     * Add '*': get ')(' --> return ')*('
     */
    ADD_MULTIPLICATION_SIGN_BETWEEN_BRACKETS()
    {
        @Override
        public String preprocess(String expression)
        {
            return expression.replaceAll(CLOSE_BRACKET_REGEX + SPACES_REGEX + OPEN_BRACKET_REGEX, ")*(");
        }
    },

    /**
     * Add '*' between any number and '('
     * Example: '5(' -> '5*('
     */
    ADD_MULTIPLICATION_SIGN_BETWEEN_NUMBER_AND_OPEN_BRACKET()
    {
        @Override
        public String preprocess(String expression)
        {
            return expression.replaceAll(NUMBER_REGEX + SPACES_REGEX + OPEN_BRACKET_REGEX, "$1*(");
        }
    },

    /**
     * Add '*' between ')' and number
     * Example: ')5' -> ')*5'
     */
    ADD_MULTIPLICATION_SIGN_BETWEEN_CLOSE_BRACKET_AND_NUMBER()
    {
        @Override
        public String preprocess(String expression)
        {
            return expression.replaceAll(CLOSE_BRACKET_REGEX + SPACES_REGEX + NUMBER_REGEX, ")*$3");
        }

    };

    @Override
    public String[] getNames()
    {
        return new String[] { this.toString() };
    }
}
