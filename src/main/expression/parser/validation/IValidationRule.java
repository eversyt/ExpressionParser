package main.expression.parser.validation;

import main.expression.parser.exceptions.ValidationRuleException;
import main.expression.parser.parser.INamed;

public interface IValidationRule extends INamed
{
    /**
     * Preliminary errors check of an expression.
     * Throw a {@link main.expression.parser.exceptions.ValidationRuleException} if a mistake in expression was found
     * 
     * @param expression String with expression
     * @throws main.expression.parser.exceptions.ValidationRuleException
     */
    public void validate(String expression) throws ValidationRuleException;
}
