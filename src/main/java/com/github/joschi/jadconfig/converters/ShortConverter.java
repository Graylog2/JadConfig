package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

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
