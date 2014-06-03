package test.expression.parser.tree;

import main.expression.parser.exceptions.UnsupportedElementException;
import main.expression.parser.exceptions.ValidationRuleException;
import main.expression.parser.parser.ExpressionParser;
import main.expression.parser.parser.IExpressionParser;
import main.expression.parser.parser.TreeNode;

import org.junit.Test;

import test.expression.parser.utils.ExpressionTestHelper;

import static main.expression.parser.operations.BasicOperations.*;
import static org.junit.Assert.assertEquals;

public class ExpressionParserTest extends ExpressionTestHelper
{
    @Test
    public void build_korrekt_tree_from_expression() throws ValidationRuleException, UnsupportedElementException
    {
        IExpressionParser parser = new ExpressionParser();
        parser.build(EXPRESSION_4);

        TreeNode head = getRootOfTreeFromParser(parser, "head");

        assertEquals(ADDITION, head.getNodeValue().getOperation());
        assertEquals(ADDITION, head.getRightChild().getNodeValue().getOperation());
        assertEquals("4", head.getRightChild().getRightChild().getNodeValue().getValue());

        TreeNode node = head.getRightChild().getLeftChild().getLeftChild();
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
