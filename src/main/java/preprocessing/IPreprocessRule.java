package preprocessing;

import parser.INamed;

public interface IPreprocessRule extends INamed
{
    /**
     * Preliminary processing of an expression
     *
     * @param expression String with expression
     * @return updated expression
     */
    public String preprocess(String expression);
}
