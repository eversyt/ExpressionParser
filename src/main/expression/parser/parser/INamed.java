package main.expression.parser.parser;

import main.expression.parser.elements.IElementType;
import main.expression.parser.operations.IOperation;
import main.expression.parser.preprocessing.IPreprocessRule;
import main.expression.parser.validation.IValidationRule;

/**
 * Common Interface for {@link main.expression.parser.elements.IElementType}, {@link main.expression.parser.operations.IOperation}, {@link main.expression.parser.preprocessing.IPreprocessRule} and {@link main.expression.parser.validation.IValidationRule}
 */
public interface INamed
{

    /**
     * Return names of an item
     * (symbols or notations for an operation or name of a rule or an elementType)
     * 
     * @return names of this item
     */
    public String[] getNames();
}
