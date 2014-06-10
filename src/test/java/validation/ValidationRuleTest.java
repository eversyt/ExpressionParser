package validation;

import exceptions.ValidationRuleException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.ExpressionTestHelper;

import static validation.BasicValidationRules.*;

public class ValidationRuleTest extends ExpressionTestHelper
{
    public static final String EXPRESSION_1_WRONG_WITH_NUMBERS = "   2.1 3,+ 13.001,.2";
    public static final String EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS = "=+ -)/ :mod ()[]   [%]";
    public static final String EXPRESSION_6 = " 1 - (2 + 3) / 5";

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
