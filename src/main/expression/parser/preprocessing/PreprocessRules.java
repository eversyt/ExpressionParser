package main.expression.parser.preprocessing;

import main.expression.parser.parser.AbstractSupportedMap;

public class PreprocessRules extends AbstractSupportedMap<IPreprocessRule>
{
    public PreprocessRules()
    {
        addBasicPreprocessRules();
    }

    private void addBasicPreprocessRules()
    {
        for (BasicPreprocessRules rule : BasicPreprocessRules.values())
        {
            addItem(rule);
        }
    }
}
