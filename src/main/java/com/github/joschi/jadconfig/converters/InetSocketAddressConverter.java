package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Converter for type {@link InetSocketAddress}
 *
 * @author jschalanda
 */
public class InetSocketAddressConverter implements Converter<InetSocketAddress> {

    private static final String SEPARATOR = ":";

    /**
     * Returns a {@link InetSocketAddress} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} representation
     * @return A {@link InetSocketAddress} instance representing the configuration parameter's value
     */
    @Override
    public InetSocketAddress convertFrom(String value) {

        InetSocketAddress result;

        try {
            if (null == value) {
                throw new NullPointerException("value must not be null!");
            }

            int lastIndexSeparator = value.lastIndexOf(SEPARATOR);

            String hostname = Strings.trim(value.substring(0, lastIndexSeparator));
            String port = Strings.trim(value.substring(lastIndexSeparator + 1, value.length()));

            if (null == hostname || hostname.isEmpty()) {
                throw new UnknownHostException("hostname must not be empty!");
            }

            InetAddress hostAddress = InetAddress.getByName(hostname);

            result = new InetSocketAddress(hostAddress, Integer.parseInt(port));
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to InetSocketAddress.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link InetSocketAddress} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(InetSocketAddress value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        if (value.getAddress() instanceof Inet6Address) {
            return String.format("[%s]%s%d", value.getAddress().getHostAddress(), SEPARATOR, value.getPort());
        } else {
            return String.format("%s%s%d", value.getHostString(), SEPARATOR, value.getPort());
        }
    }
}
