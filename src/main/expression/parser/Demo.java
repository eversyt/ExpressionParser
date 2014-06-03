package main.expression.parser;

import main.expression.parser.exceptions.CalculationException;
import main.expression.parser.exceptions.UnsupportedElementException;
import main.expression.parser.exceptions.ValidationRuleException;
import main.expression.parser.operations.CustomOperations;
import main.expression.parser.operations.IOperation;
import main.expression.parser.operations.IOperation.OperationPrintOption;
import main.expression.parser.parser.ExpressionParser;
import main.expression.parser.parser.IExpression;
import main.expression.parser.parser.IExpressionParser;

public class Demo
{
    public static void main(String[] args)
    {
        // Create new parser
        IExpressionParser parser = new ExpressionParser();
        // Adding new supported operation (factorial) to basic operations
        // like addition, subtraction, multiplication, division and exponentiation
        parser.addNewOperation(createFactorialFunction("!"));
        try
        {
            // Build binary-tree and return create new object Expression,
            // which keeps the root of the tree and is able to make different
            // operation with tree like print as String or calculate
            IExpression expression = parser.build("(4!) - (2*(1 + 2))");
            // Forming new String with expression without redundant brackets and
            // spaces
            System.out.println(expression.print() + " = " + expression.calculate());
        }
        catch (ValidationRuleException | UnsupportedElementException | CalculationException e)
        {
            e.printStackTrace();
        }
    }

    public static IOperation createFactorialFunction(String... signs)
    {
        return new CustomOperations(80, OperationPrintOption.PRINT_WITHOUT_SPACE, signs)
        {
            @Override
            public double operate(Double firstOperand, Double secondOperand) throws CalculationException
            {
                validate(secondOperand);

                /**
                 * See "Stirling's formula" in google
                 */
                return Math.round(Math.sqrt(2 * Math.PI * firstOperand)
                        * Math.pow(firstOperand / Math.E, firstOperand));
            }

            private void validate(Double secondOperand) throws CalculationException
            {
                if (secondOperand != null)
                {
                    throw new CalculationException("Calculation failed after " + this.getNames()[0]);
                }
            }
        };
    }
}
