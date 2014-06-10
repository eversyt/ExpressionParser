package parser;

import exceptions.CalculationException;
import exceptions.UnsupportedElementException;
import exceptions.ValidationRuleException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.ExpressionTestHelper;

import static org.junit.Assert.assertEquals;

public class ExpressionTest extends ExpressionTestHelper
{
    private static final double DELTA = 1e-15;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void calculate_and_print_expression() throws ValidationRuleException, CalculationException, UnsupportedElementException
    {
        IExpressionParser parser = new ExpressionParser();
        IExpression expression = parser.build(EXPRESSION_4);
        assertEquals(EXPRESSION_4_RESULT, expression.calculate(), DELTA);
        assertEquals(EXPRESSION_4_CORRECTED, expression.print());

        expression = parser.build(EXPRESSION_5);
        assertEquals(EXPRESSION_5_RESULT, expression.calculate(), DELTA);

        expression = parser.build(EXPRESSION_6);
        assertEquals(EXPRESSION_6_RESULT, expression.calculate(), DELTA);

        expression = parser.build(EXPRESSION_7);
        assertEquals(EXPRESSION_7_RESULT, expression.calculate(), DELTA);

        expression = parser.build(EXPRESSION_8);
        assertEquals(EXPRESSION_8_RESULT, expression.calculate(), DELTA);
        assertEquals(EXPRESSION_8_CORRECTED, expression.print());
    }

    @Test
    public void calculateExseption_test() throws ValidationRuleException, CalculationException, UnsupportedElementException
    {
        thrown.expect(CalculationException.class);
        IExpressionParser parser = new ExpressionParser();
        IExpression expression = parser.build(EXPRESSION_9_WRONG);
        expression.calculate();
    }

    @Test
    public void unsupported_elements_in_expression() throws ValidationRuleException, UnsupportedElementException
    {
        thrown.expect(UnsupportedElementException.class);
        IExpressionParser parser = new ExpressionParser();
        parser.setIgnoreUnsupportedElements(false);
        parser.build(EXPRESSION_10_WITH_NEW_OPERATOR);
    }

    @Test
    public void ignore_unsupported_elements_in_expression() throws ValidationRuleException, UnsupportedElementException
    {
        IExpressionParser parser = new ExpressionParser();
        parser.setIgnoreUnsupportedElements(true);
        parser.build(EXPRESSION_10_WITH_NEW_OPERATOR);
    }

    @Test
    public void calculate_expression_with_new_operation() throws ValidationRuleException, CalculationException, UnsupportedElementException
    {
        IExpressionParser parser = new ExpressionParser();
        parser.addNewOperation(MODULO);
        IExpression expression = parser.build(EXPRESSION_10_WITH_NEW_OPERATOR);
        assertEquals(EXPRESSION_10_RESULT, expression.calculate(), DELTA);
        assertEquals(EXPRESSION_10_WITH_NEW_OPERATOR, expression.print());
    }
}
