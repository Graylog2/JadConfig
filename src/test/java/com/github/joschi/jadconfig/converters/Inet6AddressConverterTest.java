package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Unit tests for {@link Inet6AddressConverter}
 *
 * @author jschalanda
 */
public class Inet6AddressConverterTest {

    private static final String INET6ADDRESS_LOCALHOST = "::1";
    private static final String INET6ADDRESS_EXAMPLE_COM = "2001:500:88:200::10";
    private static final String INET4ADDRESS_EXAMPLE_COM = "192.0.43.10";

    private Inet6AddressConverter converter;

    @Before
    public void setUp() {

        converter = new Inet6AddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assert.assertEquals(InetAddress.getByName(INET6ADDRESS_LOCALHOST), converter.convertFrom(INET6ADDRESS_LOCALHOST));
        Assert.assertEquals(InetAddress.getByName(INET6ADDRESS_EXAMPLE_COM), converter.convertFrom(INET6ADDRESS_EXAMPLE_COM));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Not a hostname#123");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInet4Address() {

        converter.convertFrom(INET4ADDRESS_EXAMPLE_COM);
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        Inet6Address loopbackInet6Address = (Inet6Address) InetAddress.getByName(INET6ADDRESS_LOCALHOST);
        Inet6Address exampleInet6Address = (Inet6Address) InetAddress.getByName(INET6ADDRESS_EXAMPLE_COM);

        Assert.assertEquals(InetAddress.getByName(INET6ADDRESS_EXAMPLE_COM).getHostAddress(), converter.convertTo(exampleInet6Address));
        Assert.assertEquals(loopbackInet6Address.getHostAddress(), converter.convertTo(loopbackInet6Address));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
