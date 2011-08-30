package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.Converter;
import de.schalanda.jadconfig.ParameterException;

/**
 * Converter for type {@link Integer}
 *
 * @author jschalanda
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer convert(String value) {

        Integer result;

        try {
            result = Integer.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Integer.", ex);
        }

        return result;
    }
}
