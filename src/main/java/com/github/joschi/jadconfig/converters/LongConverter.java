package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Long}
 *
 * @author jschalanda
 */
public class LongConverter implements Converter<Long> {

    /**
     * Returns a {@link Long} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Long} instance representing the configuration parameter's value
     */
    @Override
    public Long convertFrom(String value) {

        Long result;

        try {
            result = Long.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Long.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Long} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Long value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
