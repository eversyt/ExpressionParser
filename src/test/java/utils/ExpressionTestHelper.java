package utils;

import exceptions.CalculationException;
import operations.CustomOperations;
import operations.Operations;
import parser.ExpressionParser;
import parser.IExpressionParser;
import parser.TreeNode;

import java.lang.reflect.Field;

import static operations.IOperation.OperationPrintOption.PRINT_WITHOUT_SPACE;

public class ExpressionTestHelper
{
    public static final String EXPRESSION_1_WRONG_WITH_NUMBERS = "   2.1 3,+ 13.001,.2";
    public static final String EXPRESSION_1_WITH_DOTS = "   2.1 3.+ 13.001..2";
    public static final String EXPRESSION_1_WITHOUT_SPACE = "2.1 3,+ 13.001,.2";
    public static final String EXPECTED_NUMBER_1_EXPRESSION_1 = "2.1";
    public static final int EXPECTED_NUMBER_1_EXPRESSION_1_LENGTH = 6;
    public static final String EXPECTED_NUMBER_2_EXPRESSION_1 = "13.001";
    public static final String EXPECTED_NUMBER_3_EXPRESSION_1 = "01";
    public static final String EXPECTED_NUMBER_4_EXPRESSION_1 = ".2";

    public static final String EXPRESSION_2_WRONG_WITH_OPERATIONS_AND_BRACKETS = "=+ -)/ :mod ()[]   [%]";
    public static final String EXPRESSION_2_WITHOUT_SQUARE_BRACKETS = "=+ -)/ :mod ()()   (%)";
    public static final String EXPECTED_OPERATION_1_EXPRESSION_2 = "+";
    public static final String EXPECTED_OPERATION_2_EXPRESSION_2 = "/";
    public static final String EXPECTED_OPERATION_3_EXPRESSION_2 = ":";
    public static final String EXPECTED_OPERATION_4_EXPRESSION_2 = "mod";
    public static final String EXPECTED_OPERATION_5_EXPRESSION_2 = "%";

    public static final String EXPRESSION_3_WRONG = ")( 5( ) 6";
    public static final String EXPRESSION_3_MULTIPLICATION_1 = ")*( 5( ) 6";
    public static final String EXPRESSION_3_MULTIPLICATION_2 = ")( 5*( ) 6";
    public static final String EXPRESSION_3_MULTIPLICATION_3 = ")( 5( )*6";

    public static final String EXPRESSION_4 = "   1 + 2*3 -1 + 4  ";
    public static final String EXPRESSION_4_CORRECTED = "1 + 2*3 - 1 + 4";
    public static final double EXPRESSION_4_RESULT = 10d;

    public static final String EXPRESSION_5 = "   1--2  ";
    public static final double EXPRESSION_5_RESULT = 3d;

    public static final String EXPRESSION_6 = " 1 - (2 + 3) / 5";
    public static final double EXPRESSION_6_RESULT = 0d;

    public static final String EXPRESSION_7 = "(2 + 2 * (2 + 3)) / ((5 + 1))";
    public static final double EXPRESSION_7_RESULT = 2d;

    public static final String EXPRESSION_8 = "(-2 + 2 * (+ 2 + 3)) / ((5 + (-1)))";
    public static final String EXPRESSION_8_CORRECTED = "( - 2 + 2*( + 2 + 3))/(5 +  - 1)";
    public static final double EXPRESSION_8_RESULT = 2d;

    public static final String EXPRESSION_9_WRONG = "2 ** 2";

    public static final String EXPRESSION_10_WITH_NEW_OPERATOR = "2 + 12mod10";
    public static final double EXPRESSION_10_RESULT = 4d;

    public static final CustomOperations MODULO = new CustomOperations(55, PRINT_WITHOUT_SPACE, "mod", "%")
    {
        @Override
        public double operate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            validate(firstOperand, secondOperand);
            return firstOperand % secondOperand;
        }

        private void validate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            if ((firstOperand == null)
                    || (secondOperand == null)
                    || (secondOperand <= 0))
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }
        }
    };

    protected Operations supportedOperations = new Operations();

    public CustomOperations createOperationWithSigns(String... signs)
    {
        return new CustomOperations(60, PRINT_WITHOUT_SPACE, signs)
        {
            @Override
            public double operate(Double firstOperand, Double secondOperand)
            {
                return 0d;
            }
        };
    }

    public TreeNode getRootOfTreeFromParser(IExpressionParser parser, String nameOfField)
    {
        try
        {
            Field field = ExpressionParser.class.getDeclaredField(nameOfField);
            field.setAccessible(true);
            return (TreeNode) field.get(parser);
        } catch (NoSuchFieldException e1)
        {
            e1.printStackTrace();
        }
        catch (SecurityException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalArgumentException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e1)
        {
            e1.printStackTrace();
        }
        return null;
    }
}
