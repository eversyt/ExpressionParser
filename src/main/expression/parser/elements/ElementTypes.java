package main.expression.parser.elements;

import main.expression.parser.parser.AbstractSupportedMap;

public class ElementTypes extends AbstractSupportedMap<IElementType>
{
    public ElementTypes()
    {
        addBasicElementTypes();
    }

    private void addBasicElementTypes()
    {
        for (BasicElementTypes type : BasicElementTypes.values())
        {
            addItem(type);
        }
    }
}
