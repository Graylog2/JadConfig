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
    public Boolean convertFrom(String value) {

        if ("false".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value)) {
            return Boolean.valueOf(value);
        } else {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Boolean.");
        }
    }

    @Override
    public String convertTo(Boolean value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
