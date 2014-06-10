package parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract container for any supported
 * items {@link elements.ElementTypes}, {@link operations.Operations}, {@link preprocessing.PreprocessRules} or {@link validation.ValidationRules}
 *
 * @param <E> - type of items
 */
public abstract class AbstractSupportedMap<E extends INamed>
{
    protected Map<String, E> supportedItems = new HashMap<String, E>();

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

