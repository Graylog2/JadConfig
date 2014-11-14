package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.HostSpecifier;

/**
 * Converter for type {@link HostSpecifier}.
 */
public class HostSpecifierConverter implements Converter<HostSpecifier> {

    /**
     * Returns a {@link HostSpecifier} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link HostSpecifier} instance representing the configuration parameter's value
     */
    @Override
    public HostSpecifier convertFrom(String value) {
        final HostSpecifier result;

        try {
            result = HostSpecifier.from(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to HostSpecifier.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link HostSpecifier} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(HostSpecifier value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
