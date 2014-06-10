package elements;

import operations.IOperation;
import parser.AbstractSupportedMap;
import parser.ExpressionElement;
import parser.INamed;

/**
 * Represent types of elements in expression ({@link elements.BasicElementTypes})
 */
public interface IElementType extends INamed
{

    /**
     * Check if next element in expression after cursor position belong to this type
     * and return this element
     *
     * @param expression : expression in String
     * @param position : position of
     * @param supportedOperations : supported operations
     * @return null, if the next element don't belong to this type
     */
    public ExpressionElement check(String expression, int position, AbstractSupportedMap<IOperation> supportedOperations);
}
