package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Integer}
 *
 * @author jschalanda
 */
public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer convertFrom(String value) {

        Integer result;

        try {
            result = Integer.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Integer.", ex);
        }

        return result;
    }

    @Override
    public String convertTo(Integer value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
