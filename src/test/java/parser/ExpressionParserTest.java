package parser;

import exceptions.UnsupportedElementException;
import exceptions.ValidationRuleException;
import org.junit.Test;
import utils.ExpressionTestHelper;

import static operations.BasicOperations.*;
import static org.junit.Assert.assertEquals;

public class ExpressionParserTest extends ExpressionTestHelper
{
    public static final String EXPRESSION_4 = "   1 + 2*3 -1 + 4  ";
    public static final String EXPRESSION_5 = "   1--2  ";
    public static final String EXPRESSION_6 = " 1 - (2 + 3) / 5";
    public static final String EXPRESSION_7 = "(2 + 2 * (2 + 3)) / ((5 + 1))";

    @Test
    public void build_korrekt_tree_from_expression() throws ValidationRuleException, UnsupportedElementException
    {
        IExpressionParser parser = new ExpressionParser();
        parser.build(EXPRESSION_4);

        TreeNode head = getRootOfTreeFromParser(parser, "head");

        assertEquals(ADDITION, head.getNodeValue().getOperation());
        assertEquals(ADDITION, head.getLeftChild().getNodeValue().getOperation());

        TreeNode node = head.getLeftChild().getRightChild();
        assertEquals(SUBTRACTION, node.getNodeValue().getOperation());

        node = node.getLeftChild();
        assertEquals(MULTIPLICATION, node.getNodeValue().getOperation());
        assertEquals("2", node.getLeftChild().getNodeValue().getValue());

        parser.build(EXPRESSION_5);
        head = getRootOfTreeFromParser(parser, "head");
        assertEquals(SUBTRACTION, head.getNodeValue().getOperation());
        assertEquals(SUBTRACTION, head.getRightChild().getNodeValue().getOperation());
        assertEquals("2", head.getRightChild().getRightChild().getNodeValue().getValue());

        parser.build(EXPRESSION_6);
        head = getRootOfTreeFromParser(parser, "head");
        assertEquals(SUBTRACTION, head.getNodeValue().getOperation());
        assertEquals(DIVISION, head.getRightChild().getNodeValue().getOperation());
        assertEquals("2", head.getRightChild().getLeftChild().getLeftChild().getNodeValue().getValue());
        assertEquals("5", head.getRightChild().getRightChild().getNodeValue().getValue());
        assertEquals(ADDITION, head.getRightChild().getLeftChild().getNodeValue().getOperation());

        parser.build(EXPRESSION_7);
        head = getRootOfTreeFromParser(parser, "head");
        assertEquals(DIVISION, head.getNodeValue().getOperation());
        assertEquals(ADDITION, head.getRightChild().getNodeValue().getOperation());
        assertEquals("1", head.getRightChild().getRightChild().getNodeValue().getValue());
    }
}
