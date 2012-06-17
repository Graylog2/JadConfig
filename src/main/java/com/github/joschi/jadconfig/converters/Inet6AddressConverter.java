package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Converter for type {@link Inet6Address}
 *
 * @author jschalanda
 */
public class Inet6AddressConverter implements Converter<Inet6Address> {

    /**
     * Returns a {@link Inet6Address} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} representation
     * @return A {@link Inet6Address} instance representing the configuration parameter's value
     */
    @Override
    public Inet6Address convertFrom(String value) {

        Inet6Address result;

        try {
            if (null == value) {
                throw new NullPointerException("value must not be null!");
            }

            InetAddress inetAddress = InetAddress.getByName(value);

            if (inetAddress instanceof Inet6Address) {
                result = (Inet6Address) inetAddress;
            } else {
                throw new UnknownHostException(value);
            }
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Inet6Address.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Inet6Address} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Inet6Address value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getHostAddress();
    }
}
