package main.expression.parser.parser;

import main.expression.parser.elements.ElementTypes;
import main.expression.parser.elements.IElementType;
import main.expression.parser.exceptions.UnsupportedElementException;
import main.expression.parser.exceptions.ValidationRuleException;
import main.expression.parser.operations.IOperation;
import main.expression.parser.operations.Operations;
import main.expression.parser.preprocessing.IPreprocessRule;
import main.expression.parser.preprocessing.PreprocessRules;
import main.expression.parser.validation.IValidationRule;
import main.expression.parser.validation.ValidationRules;

import static main.expression.parser.elements.BasicElementTypes.OPEN_BRACKET;

public class ExpressionParser implements IExpressionParser
{
    private String expression;
    private AbstractSupportedMap<IOperation> supportedOperations;
    private AbstractSupportedMap<IValidationRule> supportedValidations;
    private AbstractSupportedMap<IElementType> supportedElementTypes;
    private AbstractSupportedMap<IPreprocessRule> supportedPreprocessRules;
    private TreeNode head;
    private boolean ignoreUnsupportedElements;

    public ExpressionParser()
    {
        this.supportedOperations = new Operations();
        this.supportedValidations = new ValidationRules();
        this.supportedPreprocessRules = new PreprocessRules();
        this.supportedElementTypes = new ElementTypes();
        ignoreUnsupportedElements = false;
    }

    @Override
    public Expression build(String expression) throws ValidationRuleException, UnsupportedElementException
    {
        head = null;
        this.expression = expression;

        if (!validate())
        {
            return null;
        }
        preprocess();

        return createExpression();
    }

    private Expression createExpression() throws UnsupportedElementException
    {
        head = buildTree(expression, head);
        return new Expression(expression, head);
    }

    private TreeNode buildTree(String expression, TreeNode head) throws UnsupportedElementException
    {
        int cursorPosition = 0;
        TreeNode currentNode;
        TreeNode newNode = null;
        ExpressionElement expressionElement;

        do
        {
            currentNode = newNode;
            expressionElement = getNextElement(expression, cursorPosition);
            if (expressionElement == null)
            {
                cursorPosition++;
                continue;
            }

            cursorPosition += expressionElement.getLengthWithSpace();
            if (expressionElement.getType().equals(OPEN_BRACKET))
            {
                String substring = getSubstringInBrackets(expression, cursorPosition);
                newNode = buildTree(substring.trim(), null);
                cursorPosition += substring.length() + 1;
                newNode.setPriority(OPEN_BRACKET.getPriority());
            }
            else
            {
                newNode = new TreeNode(expressionElement, null, null);
            }
            head = addNodeInTree(head, currentNode, newNode);
        }
        while (cursorPosition < expression.length());

        return head;
    }

    private TreeNode addNodeInTree(TreeNode head, TreeNode currentNode, TreeNode newNode)
    {
        if (head == null)
        {
            head = newNode;
        }
        else if (currentNode.getPriority() <= newNode.getPriority())
        {
            connectAsRightChild(currentNode, newNode);
        }
        else
        {
            TreeNode parent = currentNode.getParent();

            if (parent == null)
            {
                head = newNode;
                connectAsLeftChild(newNode, currentNode);
            }
            else if (parent.getPriority() <= newNode.getPriority())
            {
                connectAsRightChild(parent, newNode);
                connectAsLeftChild(newNode, currentNode);
            }
            else
            {
                head = addNodeInTree(head, parent, newNode);
            }
        }
        return head;
    }

    private void connectAsLeftChild(TreeNode parentNode, TreeNode childNode)
    {
        parentNode.setLeftChild(childNode);
        childNode.setParent(parentNode);
    }

    private void connectAsRightChild(TreeNode parentNode, TreeNode childNode)
    {
        parentNode.setRightChild(childNode);
        childNode.setParent(parentNode);
    }

    private ExpressionElement getNextElement(String expression, int cursorPosition) throws UnsupportedElementException
    {
        ExpressionElement result;
        for (IElementType elementType : supportedElementTypes.getMap().values())
        {
            result = elementType.check(expression, cursorPosition, supportedOperations);
            if (result != null)
            {
                return result;
            }
        }

        if (!ignoreUnsupportedElements)
        {
            char unsupportedElement = expression.substring(cursorPosition).trim().charAt(0);

            throw new UnsupportedElementException("Unsupported element found: " + unsupportedElement
                    , this.expression.lastIndexOf(unsupportedElement));
        }
        return null;
    }

    private String getSubstringInBrackets(String expression, int cursorPosition)
    {
        int bracketCounter = 0;
        String string = expression.substring(cursorPosition);

        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            if ((bracketCounter == 0) && (c == ')'))
            {
                return string.substring(0, i);
            }
            if (c == '(')
            {
                bracketCounter++;
            }
            if (c == ')')
            {
                bracketCounter--;
            }
        }
        return expression;
    }

    private boolean validate() throws ValidationRuleException
    {
        if ((expression == null) || (expression.length() == 0))
        {
            return false;
        }

        for (IValidationRule rule : supportedValidations.getMap().values())
        {
            rule.validate(expression);
        }
        return true;
    }

    private void preprocess()
    {
        for (IPreprocessRule rule : supportedPreprocessRules.getMap().values())
        {
            expression = rule.preprocess(expression);
        }
    }

    @Override
    public boolean addNewOperation(IOperation operation)
    {
        return supportedOperations.addItem(operation);
    }

    @Override
    public boolean addNewValidationRule(IValidationRule rule)
    {
        return supportedValidations.addItem(rule);
    }

    @Override
    public boolean addNewPreprocessRule(IPreprocessRule rule)
    {
        return supportedPreprocessRules.addItem(rule);
    }

    @Override
    public boolean addNewElementType(IElementType type)
    {
        return supportedElementTypes.addItem(type);
    }

    public boolean isIgnoreUnsupportedElements()
    {
        return ignoreUnsupportedElements;
    }

    @Override
    public void setIgnoreUnsupportedElements(boolean ignoreUnsupportedElements)
    {
        this.ignoreUnsupportedElements = ignoreUnsupportedElements;
    }
}
