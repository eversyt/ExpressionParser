package test.expression.parser.validation;

import main.expression.parser.exceptions.ValidationRuleException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import test.expression.parser.utils.ExpressionTestHelper;

import static main.expression.parser.validation.BasicValidationRules.*;

public class ValidationRuleTest extends ExpressionTestHelper
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void unpaired_brackets_error() throws ValidationRuleException
    {
        thrown.expect(ValidationRuleException.class);
        UNPAIRED_BRACKETS.validate(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS);
    }

    @Test
    public void no_numbers_in_expression_error() throws ValidationRuleException
    {
        thrown.expect(ValidationRuleException.class);
        NO_NUMBERS_IN_EXPRESSION.validate(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS);
    }

    @Test
    public void numbers_in_sequence() throws ValidationRuleException
    {
        thrown.expect(ValidationRuleException.class);
        NUMBERS_IN_SEQUENCE.validate(EXPRESSION_1_WRONG_WITH_NUMBERS);
    }

    @Test
    public void no_errors() throws ValidationRuleException
    {
        UNPAIRED_BRACKETS.validate(EXPRESSION_6);
        NO_NUMBERS_IN_EXPRESSION.validate(EXPRESSION_6);
    }
}
