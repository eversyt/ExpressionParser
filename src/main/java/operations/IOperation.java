package operations;

import exceptions.CalculationException;
import parser.INamed;

/**
 * Represent interface for new and basic operation, see also {@link operations.CustomOperations}
 */
public interface IOperation extends INamed
{

    /**
     * Enum with types of notations of operations.
     */
    public enum OperationPrintOption
    {
        /**
         * Type represents operations, which notate with space between operation and its operands.
         * Example : ADDITION : a + b
         */
        PRINT_WITH_SPACE,

        /**
         * Type represents operations, which notate without space between operation and its operands.
         * Example : MULTIPLICATION : a*b
         */
        PRINT_WITHOUT_SPACE,
    }

    /**
     * Calculate the result of this operation
     *
     * @return result of a function
     * @throws exceptions.CalculationException
     */
    public double operate(Double firstOperand, Double secondOperand) throws CalculationException;

    /**
     * Return String with correct writing of this operation
     * Example: 'a + b' and not 'a+b'
     *
     * @return correct writing of operation
     */
    public String print();

    /**
     * Return a priority of this operation corresponding to other operation.
     * From 0 to 98. Priority 99 belong to numbers, 100 - to brackets
     *
     * @return priority
     */
    public int getPriority();
}
