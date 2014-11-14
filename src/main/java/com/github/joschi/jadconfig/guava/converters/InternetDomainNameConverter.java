package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.InternetDomainName;

/**
 * Converter for type {@link InternetDomainName}.
 */
public class InternetDomainNameConverter implements Converter<InternetDomainName> {

    /**
     * Returns a {@link InternetDomainName} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return An {@link InternetDomainName} instance representing the configuration parameter's value
     */
    @Override
    public InternetDomainName convertFrom(String value) {
        final InternetDomainName result;

        try {
            result = InternetDomainName.from(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to InternetDomainName.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link InternetDomainName} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(InternetDomainName value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
