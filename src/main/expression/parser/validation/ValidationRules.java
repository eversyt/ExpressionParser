package main.expression.parser.validation;

import main.expression.parser.parser.AbstractSupportedMap;

public class ValidationRules extends AbstractSupportedMap<IValidationRule>
{
    public ValidationRules()
    {
        addBasicValidations();
    }

    private void addBasicValidations()
    {
        for (BasicValidationRules rule : BasicValidationRules.values())
        {
            addItem(rule);
        }
    }
}
