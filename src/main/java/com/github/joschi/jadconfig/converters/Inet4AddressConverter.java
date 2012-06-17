package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Converter for type {@link Inet4Address}
 *
 * @author jschalanda
 */
public class Inet4AddressConverter implements Converter<Inet4Address> {

    /**
     * Returns a {@link Inet4Address} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} representation
     * @return A {@link Inet4Address} instance representing the configuration parameter's value
     */
    @Override
    public Inet4Address convertFrom(String value) {

        Inet4Address result;

        try {
            if (null == value) {
                throw new NullPointerException("value must not be null!");
            }

            InetAddress inetAddress = InetAddress.getByName(value);

            if (inetAddress instanceof Inet4Address) {
                result = (Inet4Address) inetAddress;
            } else {
                throw new UnknownHostException(value);
            }
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Inet4Address.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Inet4Address} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Inet4Address value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getHostAddress();
    }
}
