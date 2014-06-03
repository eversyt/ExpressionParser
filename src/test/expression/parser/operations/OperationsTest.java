package test.expression.parser.operations;

import main.expression.parser.exceptions.CalculationException;

import org.junit.Test;

import test.expression.parser.utils.ExpressionTestHelper;

import static main.expression.parser.operations.BasicOperations.*;
import static org.junit.Assert.assertEquals;

public class OperationsTest extends ExpressionTestHelper
{
    @Test
    public void add_and_remove_operations()
    {
        assertEquals(6, supportedOperations.getMap().size());

        supportedOperations.addItem(MODULO);
        assertEquals(8, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("%%"));
        assertEquals(8, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns(")"));
        assertEquals(8, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("("));
        assertEquals(8, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("]"));
        assertEquals(8, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("sqrt", "["));
        assertEquals(8, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("Sqrt"));
        assertEquals(9, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("an operation"));
        assertEquals(9, supportedOperations.getMap().size());

        supportedOperations.addItem(createOperationWithSigns("&", "$", "\"", "?", "_"));
        assertEquals(14, supportedOperations.getMap().size());

        supportedOperations.removeItem("&");
        assertEquals(13, supportedOperations.getMap().size());
    }

    @Test(expected = CalculationException.class)
    public void calculation_error_in_addition() throws CalculationException
    {
        ADDITION.operate(null, null);
    }

    @Test(expected = CalculationException.class)
    public void calculation_error_in_divition() throws CalculationException
    {
        DIVISION.operate(3d, 0d);
    }

    @Test(expected = CalculationException.class)
    public void calculation_error_in_substraction() throws CalculationException
    {
        SUBTRACTION.operate(null, null);
    }

    @Test(expected = CalculationException.class)
    public void calculation_error_in_multiplication() throws CalculationException
    {
        MULTIPLICATION.operate(null, 0d);
    }

    @Test(expected = CalculationException.class)
    public void calculation_error_in_powering() throws CalculationException
    {
        POWER.operate(0d, 0d);
    }
}
