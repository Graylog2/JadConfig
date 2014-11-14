package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.HostAndPort;

/**
 * Converter for type {@link HostAndPort}.
 */
public class HostAndPortConverter implements Converter<HostAndPort> {

    /**
     * Returns a {@link HostAndPort} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link HostAndPort} instance representing the configuration parameter's value
     */
    @Override
    public HostAndPort convertFrom(String value) {
        final HostAndPort result;

        try {
            result = HostAndPort.fromString(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to HostAndPort.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link HostAndPort} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(HostAndPort value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
