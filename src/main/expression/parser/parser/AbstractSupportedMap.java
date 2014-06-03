package main.expression.parser.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import main.expression.parser.elements.ElementTypes;
import main.expression.parser.operations.Operations;
import main.expression.parser.preprocessing.PreprocessRules;
import main.expression.parser.validation.ValidationRules;

/**
 * Abstract container for any supported
 * items {@link main.expression.parser.elements.ElementTypes}, {@link main.expression.parser.operations.Operations}, {@link main.expression.parser.preprocessing.PreprocessRules} or {@link main.expression.parser.validation.ValidationRules}
 * 
 * @param <E> - type of items
 */
public abstract class AbstractSupportedMap<E extends INamed>
{
    protected Map<String, E> supportedItems = new HashMap<>();

    public boolean addItem(E item)
    {
        if (item == null)
        {
            return false;
        }

        for (String name : item.getNames())
        {
            supportedItems.put(name, item);
        }
        return true;
    }

    public boolean removeItem(String name)
    {
        return supportedItems.remove(name) != null;
    }

    public Map<String, E> getMap()
    {
        return Collections.unmodifiableMap(supportedItems);
    }
}
