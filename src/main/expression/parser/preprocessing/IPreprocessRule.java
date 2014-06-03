package main.expression.parser.preprocessing;

import main.expression.parser.parser.INamed;

public interface IPreprocessRule extends INamed
{
    /**
     * Preliminary processing of an expression
     * 
     * @param expression
     * @return updated expression
     */
    public String preprocess(String expression);
}
