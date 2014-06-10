package validation;

import exceptions.ValidationRuleException;
import parser.INamed;

public interface IValidationRule extends INamed
{
    /**
     * Preliminary errors check of an expression.
     * Throw a {@link exceptions.ValidationRuleException} if a mistake in expression was found
     *
     * @param expression String with expression
     * @throws exceptions.ValidationRuleException
     */
    public void validate(String expression) throws ValidationRuleException;
}
