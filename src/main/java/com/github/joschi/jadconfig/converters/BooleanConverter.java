package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Boolean}
 *
 * @author jschalanda
 */
public class BooleanConverter implements Converter<Boolean> {

    @Override
    public Boolean convert(String value) {

        if ("false".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value)) {
            return Boolean.valueOf(value);
        } else {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Boolean.");
        }
    }
}
