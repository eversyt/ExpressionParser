package parser;

import elements.IElementType;
import exceptions.UnsupportedElementException;
import exceptions.ValidationRuleException;
import operations.IOperation;
import preprocessing.IPreprocessRule;
import validation.IValidationRule;

/**
 * Parser of an arithmetic expression.
 */
public interface IExpressionParser
{
    /**
     * Build the binary tree of the expression and return {@link Expression}.
     * Throw a {@link exceptions.ValidationRuleException} if the expression is not valid ({@link validation.IValidationRule})
     * Throw a {@link exceptions.UnsupportedElementException} in case of founding an unsupported
     * element in expression
     *
     * @param expression as a String
     * @return Expression.class
     * @throws exceptions.ValidationRuleException
     * @throws exceptions.UnsupportedElementException
     */
    public Expression build(String expression) throws ValidationRuleException, UnsupportedElementException;

    /**
     * Add new operation
     *
     * @param operation  implementation of {@link operations.IOperation} interface
     * @return true if the sign of operation is valid, otherwise false
     */
    public boolean addNewOperation(IOperation operation);

    /**
     * Add new rule of validation expressions
     *
     * @param rule implementation of {@link validation.IValidationRule} interface
     * @return true if addition pass succeed
     */
    public boolean addNewValidationRule(IValidationRule rule);

    /**
     * Add new rule for pre-processing of expressions
     *
     * @param rule implementation of {@link preprocessing.IPreprocessRule} interface
     * @return true if new rule was added
     */
    public boolean addNewPreprocessRule(IPreprocessRule rule);

    /**
     * Add new supported ElementType in parser (like constant or variable)
     * Please note: by adding new ElementType new implementation of {@link parser.IExpression} is needed!
     * Otherwise you can add constants as a new operation with priority 99 like numbers
     *
     * @param type implementation of {@link elements.IElementType} interface
     * @return true if new type was added
     */
    public boolean addNewElementType(IElementType type);

    /**
     * Set parameter ignoreUnsupportedElements. If it's true, parser try to ignore
     * unsupported elements in expression and build a binary-tree without them. Use
     * on your own risk. The binary-tree can be unqualified.
     * Otherwise throw an {@link exceptions.CalculationException} by founding an irrelevant element.
     *
     * @param ignoreUnsupportedElements by default is false
     */
    public void setIgnoreUnsupportedElements(boolean ignoreUnsupportedElements);
}

