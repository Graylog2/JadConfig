package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.primitives.UnsignedInteger;

/**
 * Converter for type {@link UnsignedInteger}.
 */
public class UnsignedIntegerConverter implements Converter<UnsignedInteger> {

    /**
     * Returns a {@link UnsignedInteger} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link UnsignedInteger} instance representing the configuration parameter's value
     */
    @Override
    public UnsignedInteger convertFrom(String value) {
        final UnsignedInteger result;

        try {
            result = UnsignedInteger.valueOf(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to UnsignedInteger.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link UnsignedInteger} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(UnsignedInteger value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
