package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.Converter;
import de.schalanda.jadconfig.ParameterException;

/**
 * Converter for type {@link String}
 *
 * @author jschalanda
 */
public class StringConverter implements Converter<String> {

    @Override
    public String convert(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to String.");
        }

        return value;
    }
}
