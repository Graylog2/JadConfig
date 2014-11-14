package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.MediaType;

/**
 * Converter for type {@link MediaType}.
 */
public class MediaTypeConverter implements Converter<MediaType> {

    /**
     * Returns a {@link MediaType} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link MediaType} instance representing the configuration parameter's value
     */
    @Override
    public MediaType convertFrom(String value) {
        final MediaType result;

        try {
            result = MediaType.parse(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to MediaType.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link MediaType} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(MediaType value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
