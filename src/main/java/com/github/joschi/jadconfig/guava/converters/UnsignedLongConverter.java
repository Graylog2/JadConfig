package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.primitives.UnsignedLong;

/**
 * Converter for type {@link UnsignedLong}.
 */
public class UnsignedLongConverter implements Converter<UnsignedLong> {

    /**
     * Returns a {@link UnsignedLong} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link UnsignedLong} instance representing the configuration parameter's value
     */
    @Override
    public UnsignedLong convertFrom(String value) {
        final UnsignedLong result;

        try {
            result = UnsignedLong.valueOf(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to UnsignedLong.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link UnsignedLong} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(UnsignedLong value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
