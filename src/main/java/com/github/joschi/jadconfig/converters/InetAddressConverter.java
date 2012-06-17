package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.net.InetAddress;

/**
 * Converter for type {@link InetAddress}
 *
 * @author jschalanda
 */
public class InetAddressConverter implements Converter<InetAddress> {

    /**
     * Returns a {@link InetAddress} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} representation
     * @return A {@link InetAddress} instance representing the configuration parameter's value
     */
    @Override
    public InetAddress convertFrom(String value) {

        InetAddress result;

        try {
            if (null == value) {
                throw new NullPointerException("value must not be null!");
            }

            result = InetAddress.getByName(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to InetAddress.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link java.net.InetAddress} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(InetAddress value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getCanonicalHostName();
    }
}
