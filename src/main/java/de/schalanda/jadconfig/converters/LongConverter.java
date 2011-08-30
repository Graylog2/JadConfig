package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.Converter;
import de.schalanda.jadconfig.ParameterException;

/**
 * Converter for type {@link Long}
 *
 * @author jschalanda
 */
public class LongConverter implements Converter<Long> {

    @Override
    public Long convert(String value) {

        Long result;

        try {
            result = Long.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Long.", ex);
        }

        return result;
    }
}
