package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link String}
 *
 * @author jschalanda
 */
public class StringConverter implements Converter<String> {

    /**
     * Returns a {@link String} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link String} instance representing the configuration parameter's value
     */
    @Override
    public String convertFrom(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to String.");
        }

        return value;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(String value) {

        return convertFrom(value);
    }
}
