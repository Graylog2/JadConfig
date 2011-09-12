package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link String}
 *
 * @author jschalanda
 */
public class StringConverter implements Converter<String> {

    @Override
    public String convertFrom(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to String.");
        }

        return value;
    }

    @Override
    public String convertTo(String value) {

        return convertFrom(value);
    }
}
