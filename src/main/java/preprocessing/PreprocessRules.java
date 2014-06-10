package preprocessing;

import parser.AbstractSupportedMap;

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
