package main.expression.parser.operations;

import main.expression.parser.exceptions.CalculationException;

import static main.expression.parser.operations.IOperation.OperationPrintOption.*;

public enum BasicOperations implements IOperation
{
    ADDITION(10, PRINT_WITH_SPACE, "+")
    {
        @Override
        public double operate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            validate(secondOperand);
            if (firstOperand == null)
            {
                return secondOperand;
            }
            return firstOperand + secondOperand;
        }

        private void validate(Double secondOperand) throws CalculationException
        {
            if (secondOperand == null)
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }
        }
    },

    SUBTRACTION(20, PRINT_WITH_SPACE, "-")
    {
        @Override
        public double operate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            validate(secondOperand);
            if (firstOperand == null)
            {
                return -secondOperand;
            }
            return firstOperand - secondOperand;
        }

        private void validate(Double secondOperand) throws CalculationException
        {
            if (secondOperand == null)
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }
        }
    },

    DIVISION(30, PRINT_WITHOUT_SPACE, "/", ":")
    {
        @Override
        public double operate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            validate(firstOperand, secondOperand);
            return firstOperand / secondOperand;
        }

        private void validate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            if ((firstOperand == null)
                    || (secondOperand == null)
                    || (secondOperand == 0))
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }
        }
    },

    MULTIPLICATION(40, PRINT_WITHOUT_SPACE, "*")
    {
        @Override
        public double operate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            validate(firstOperand, secondOperand);
            return firstOperand * secondOperand;
        }

        private void validate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            if ((firstOperand == null)
                    || (secondOperand == null))
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }
        }
    },

    POWER(50, PRINT_WITHOUT_SPACE, "^")
    {
        @Override
        public double operate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            validate(firstOperand, secondOperand);
            return Math.pow(firstOperand, secondOperand);
        }

        private void validate(Double firstOperand, Double secondOperand) throws CalculationException
        {
            if ((firstOperand == null)
                    || (secondOperand == null)
                    || ((firstOperand == 0) && (secondOperand == 0)))
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }

            double result = Math.pow(firstOperand, secondOperand);
            if (Double.isNaN(result)
                    || Double.isInfinite(result))
            {
                throw new CalculationException("Calculation failed after " + this.getNames()[0] + " sign.");
            }
        }
    };

    private int priority;
    private OperationPrintOption printOption;
    private String[] signs;

    BasicOperations(int priority, OperationPrintOption printOption, String... signs)
    {
        this.priority = priority;
        this.printOption = printOption;
        this.signs = signs;
    }

    @Override
    public int getPriority()
    {
        return priority;
    }

    @Override
    public String print()
    {
        switch (printOption)
        {
            case PRINT_WITH_SPACE:
                return " " + signs[0] + " ";
            case PRINT_WITHOUT_SPACE:
            default:
                return signs[0];
        }
    }

    @Override
    public String[] getNames()
    {
        return signs;
    }
}