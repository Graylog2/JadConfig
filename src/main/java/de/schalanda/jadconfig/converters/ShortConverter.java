package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.Converter;
import de.schalanda.jadconfig.ParameterException;

/**
 * Converter for type {@link Short}
 *
 * @author jschalanda
 */
public class ShortConverter implements Converter<Short> {

    @Override
    public Short convert(String value) {

        Short result;

        try {
            result = Short.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Short.", ex);
        }

        return result;
    }
}
