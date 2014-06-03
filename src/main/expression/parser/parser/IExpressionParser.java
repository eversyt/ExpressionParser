package main.expression.parser.parser;

import main.expression.parser.elements.IElementType;
import main.expression.parser.exceptions.CalculationException;
import main.expression.parser.exceptions.UnsupportedElementException;
import main.expression.parser.exceptions.ValidationRuleException;
import main.expression.parser.operations.IOperation;
import main.expression.parser.preprocessing.IPreprocessRule;
import main.expression.parser.validation.IValidationRule;

/**
 * Parser of an arithmetic expression.
 */
public interface IExpressionParser
{
    /**
     * Build the binary tree of the expression and return {@link main.expression.parser.parser.Expression}.
     * Throw a {@link main.expression.parser.exceptions.ValidationRuleException} if the expression is not valid ({@link main.expression.parser.validation.IValidationRule})
     * Throw a {@link main.expression.parser.exceptions.UnsupportedElementException} in case of founding an unsupported
     * element in expression
     * 
     * @param expression as a String
     * @return Expression.class
     * @throws main.expression.parser.exceptions.ValidationRuleException
     * @throws main.expression.parser.exceptions.UnsupportedElementException
     */
    public Expression build(String expression) throws ValidationRuleException, UnsupportedElementException;

    /**
     * Add new operation
     * 
     * @param operation  implementation of {@link main.expression.parser.operations.IOperation} interface
     * @return true if the sign of operation is valid, otherwise false
     */
    public boolean addNewOperation(IOperation operation);

    /**
     * Add new rule of validation expressions
     * 
     * @param rule implementation of {@link main.expression.parser.validation.IValidationRule} interface
     * @return true if addition pass succeed
     */
    public boolean addNewValidationRule(IValidationRule rule);

    /**
     * Add new rule for pre-processing of expressions
     * 
     * @param rule implementation of {@link main.expression.parser.preprocessing.IPreprocessRule} interface
     * @return true if new rule was added
     */
    public boolean addNewPreprocessRule(IPreprocessRule rule);

    /**
     * Add new supported ElementType in parser (like constant or variable)
     * Please note: by adding new ElementType new implementation of {@link main.expression.parser.parser.IExpression} is needed!
     * Otherwise you can add constants as a new operation with priority 99 like numbers
     * 
     * @param type implementation of {@link main.expression.parser.elements.IElementType} interface
     * @return true if new type was added
     */
    public boolean addNewElementType(IElementType type);

    /**
     * Set parameter ignoreUnsupportedElements. If it's true, parser try to ignore
     * unsupported elements in expression and build a binary-tree without them. Use
     * on your own risk. The binary-tree can be unqualified.
     * Otherwise throw an {@link main.expression.parser.exceptions.CalculationException} by founding an irrelevant element.
     * 
     * @param ignoreUnsupportedElements by default is false
     */
    public void setIgnoreUnsupportedElements(boolean ignoreUnsupportedElements);
}
