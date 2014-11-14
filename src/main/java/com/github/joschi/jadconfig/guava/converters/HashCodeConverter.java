package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.hash.HashCode;

/**
 * Converter for type {@link HashCode}.
 */
public class HashCodeConverter implements Converter<HashCode> {

    /**
     * Returns a {@link HashCode} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link HashCode} instance representing the configuration parameter's value
     */
    @Override
    public HashCode convertFrom(String value) {
        final HashCode result;

        try {
            result = HashCode.fromString(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to HashCode.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link HashCode} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(HashCode value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
