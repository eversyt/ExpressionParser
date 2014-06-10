package parser;

import elements.BasicElementTypes;
import exceptions.CalculationException;

public class Expression implements IExpression
{
    private TreeNode head;
    private String expression;

    public Expression(String expression, TreeNode head)
    {
        this.expression = expression;
        this.head = head;
        if (expression == null)
        {
            this.expression = "";
        }
    }

    public String getExpression()
    {
        return expression.trim();
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    @Override
    public double calculate() throws CalculationException
    {
        return head == null ? 0d : calculate(head);
    }

    private Double calculate(TreeNode node) throws CalculationException
    {
        if (node == null)
        {
            return null;
        }
        ExpressionElement element = node.getNodeValue();
        switch ((BasicElementTypes) element.getType())
        {
            case NUMBER:
                return Double.parseDouble(element.getValue());
            case OPERATION:
                return element.getOperation().operate(calculate(node.getLeftChild()), calculate(node.getRightChild()));
            default:
                break;
        }
        return null;
    }

    @Override
    public String print()
    {
        return head == null ? "" : print(head);
    }

    private String print(TreeNode node)
    {
        if (node == null)
        {
            return "";
        }
        ExpressionElement element = node.getNodeValue();
        if (element.getOperation()== null)
        {
            return element.getValue();
        }
        else
        {
            String result = print(node.getLeftChild()) + element.getOperation().print() + print(node.getRightChild());
            if ((node.getParent() != null)
                    && (node.getParent().getNodeValue().getPriority() > node.getNodeValue().getPriority()))
            {
                return "(" + result + ")";
            }
            return result;
        }
    }
}
