package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Unit tests for {@link Inet4AddressConverter}
 *
 * @author jschalanda
 */
public class Inet4AddressConverterTest {

    private static final String INET4ADDRESS_LOCALHOST = "127.0.0.1";
    private static final String INET4ADDRESS_EXAMPLE_COM = "192.0.43.10";
    private static final String INET6ADDRESS_EXAMPLE_COM = "2001:500:88:200::10";

    private Inet4AddressConverter converter;

    @Before
    public void setUp() {

        converter = new Inet4AddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assert.assertEquals(InetAddress.getLoopbackAddress(), converter.convertFrom(INET4ADDRESS_LOCALHOST));
        Assert.assertEquals(InetAddress.getByName(INET4ADDRESS_EXAMPLE_COM), converter.convertFrom(INET4ADDRESS_EXAMPLE_COM));
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
    public void testConvertFromInet6Address() {

        converter.convertFrom(INET6ADDRESS_EXAMPLE_COM);
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        Inet4Address loopbackInet4Address = (Inet4Address) InetAddress.getByName(INET4ADDRESS_LOCALHOST);
        Inet4Address exampleInet4Address = (Inet4Address) InetAddress.getByName(INET4ADDRESS_EXAMPLE_COM);

        Assert.assertEquals(InetAddress.getByName(INET4ADDRESS_EXAMPLE_COM).getHostAddress(), converter.convertTo(exampleInet4Address));
        Assert.assertEquals(loopbackInet4Address.getHostAddress(), converter.convertTo(loopbackInet4Address));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
