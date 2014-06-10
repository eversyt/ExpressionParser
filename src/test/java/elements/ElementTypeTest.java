package elements;

import org.junit.Before;
import org.junit.Test;
import parser.ExpressionElement;
import utils.ExpressionTestHelper;

import static elements.BasicElementTypes.*;
import static operations.BasicOperations.ADDITION;
import static operations.BasicOperations.DIVISION;
import static org.junit.Assert.assertEquals;

public class ElementTypeTest extends ExpressionTestHelper
{
    public static final String EXPRESSION_1_WRONG_WITH_NUMBERS = "   2.1 3,+ 13.001,.2";
    public static final String EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS = "=+ -)/ :mod ()[]   [%]";

    @Before
    public void theory()
    {
        supportedOperations.addItem(MODULO);
    }

    @Test
    public void search_of_numbers_in_expression()
    {
        ExpressionElement element;
        element = NUMBER.check(EXPRESSION_1_WRONG_WITH_NUMBERS, 0, supportedOperations);
        assertEquals(EXPECTED_NUMBER_1_EXPRESSION_1, element.getValue());
        assertEquals(EXPECTED_NUMBER_1_EXPRESSION_1_LENGTH, element.getLengthWithSpace());
        assertEquals(NUMBER, element.getType());

        element = NUMBER.check(EXPRESSION_1_WRONG_WITH_NUMBERS, 8, supportedOperations);
        assertEquals(null, element);

        element = NUMBER.check(EXPRESSION_1_WRONG_WITH_NUMBERS, 11, supportedOperations);
        assertEquals(EXPECTED_NUMBER_2_EXPRESSION_1, element.getValue());

        element = NUMBER.check(EXPRESSION_1_WRONG_WITH_NUMBERS, 15, supportedOperations);
        assertEquals(EXPECTED_NUMBER_3_EXPRESSION_1, element.getValue());

        element = NUMBER.check(EXPRESSION_1_WRONG_WITH_NUMBERS, 18, supportedOperations);
        assertEquals(EXPECTED_NUMBER_4_EXPRESSION_1, element.getValue());
    }

    @Test
    public void search_of_operations_in_expression()
    {
        ExpressionElement element;
        element = OPERATION.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 1, supportedOperations);
        assertEquals(EXPECTED_OPERATION_1_EXPRESSION_2, element.getValue());
        assertEquals(ADDITION, element.getOperation());

        element = OPERATION.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 5, supportedOperations);
        assertEquals(EXPECTED_OPERATION_2_EXPRESSION_2, element.getValue());
        assertEquals(DIVISION, element.getOperation());

        element = OPERATION.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 6, supportedOperations);
        assertEquals(EXPECTED_OPERATION_3_EXPRESSION_2, element.getValue());
        assertEquals(DIVISION, element.getOperation());

        element = OPERATION.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 8, supportedOperations);
        assertEquals(EXPECTED_OPERATION_4_EXPRESSION_2, element.getValue());
        assertEquals(3, element.getLengthWithSpace());
        assertEquals(MODULO, element.getOperation());

        element = OPERATION.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 20, supportedOperations);
        assertEquals(EXPECTED_OPERATION_5_EXPRESSION_2, element.getValue());
        assertEquals(MODULO, element.getOperation());

    }

    @Test
    public void search_of_brackets_in_expression()
    {
        ExpressionElement element;
        element = OPEN_BRACKET.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 0, supportedOperations);
        assertEquals(null, element);

        element = OPEN_BRACKET.check(EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS, 12, supportedOperations);
        assertEquals("(", element.getValue());
        assertEquals(1, element.getLengthWithSpace());
        assertEquals(OPEN_BRACKET, element.getType());
    }

    @Test
    public void hz()
    {
        ElementTypes supportedTypes = new ElementTypes();
        assertEquals(4, supportedTypes.getMap().size());
    }
}
