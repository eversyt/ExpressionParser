package parser;

/**
 * Common Interface for {@link elements.IElementType}, {@link operations.IOperation},
 * {@link preprocessing.IPreprocessRule} and {@link validation.IValidationRule}
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
