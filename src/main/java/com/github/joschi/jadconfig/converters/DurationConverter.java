package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.Duration;

/**
 * Converter for type {@link Duration}
 *
 * @author jschalanda
 */
public class DurationConverter implements Converter<Duration> {

    /**
     * Returns a {@link Duration} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Duration} instance representing the configuration parameter's value
     */
    @Override
    public Duration convertFrom(String value) {
        try {
            return Duration.parse(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Duration.", ex);
        }

    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Duration} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Duration value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
