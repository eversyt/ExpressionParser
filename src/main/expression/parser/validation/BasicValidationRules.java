package main.expression.parser.validation;

import main.expression.parser.exceptions.ValidationRuleException;

import static main.expression.parser.elements.BasicElementTypes.NUMBER_REGEX;

public enum BasicValidationRules implements IValidationRule
{
    /**
     * Check a number of open and close brackets to avoid '((3 - 1) * 2'
     */
    UNPAIRED_BRACKETS()
    {
        @Override
        public void validate(String expression) throws ValidationRuleException
        {
            if (expression.replaceAll("[^(]", "").length() != expression.replaceAll("[^)]", "").length())
            {
                throw new ValidationRuleException("Validation fails: " + this.toString(), 0);
            }
        }
    },

    /**
     * Check close brackets to avoid '(3 - 1)) * 2('
     */
    CLOSE_BRACKET_WITHOUT_OPEN_ONE()
    {
        @Override
        public void validate(String expression) throws ValidationRuleException
        {
            int bracketCounter = 0;

            for (int i = 0; i < expression.length(); i++)
            {
                char c = expression.charAt(i);
                if (bracketCounter < 0)
                {
                    throw new ValidationRuleException("Validation fails: " + this.toString(), i);
                }
                if (c == '(')
                {
                    bracketCounter++;
                }
                if (c == ')')
                {
                    bracketCounter--;
                }
            }
        }
    },

    /**
     * Check to avoid expressions without any number: '(+)'
     */
    NO_NUMBERS_IN_EXPRESSION()
    {
        @Override
        public void validate(String expression) throws ValidationRuleException
        {
            if (expression.replaceAll(NUMBER_REGEX, "").length() == expression.length())
            {
                throw new ValidationRuleException("Validation fails: " + this.toString(), 0);
            }
        }
    },

    /**
     * Check to avoid expressions with numbers, which follow one by one
     * without any operations between: '2.1 54'
     */
    NUMBERS_IN_SEQUENCE()
    {
        @Override
        public void validate(String expression) throws ValidationRuleException
        {
            if (expression.replaceAll(NUMBER_REGEX + "(\\s+)" + NUMBER_REGEX, "").length() != expression.length())
            {
                throw new ValidationRuleException("Validation fails: " + this.toString(), 0);
            }
        }
    };

    @Override
    public String[] getNames()
    {
        return new String[] { this.toString() };
    }
}