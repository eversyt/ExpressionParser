package parser;

import exceptions.CalculationException;

/**
 * Represent an expression in binary tree representation
 */
public interface IExpression
{
    /**
     * Calculate the result of the expression or throw an exception
     *
     * @return result
     * @throws CalculationException
     */
    public double calculate() throws CalculationException;

    /**
     * Form a string from the expression without redundant brackets and spaces
     *
     * @return the string with the expression
     */
    public String print();
}
