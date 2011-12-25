package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Unit tests for {@link InetSocketAddressConverter}
 *
 * @author jschalanda
 */
public class InetSocketAddressConverterTest {

    private InetSocketAddressConverter converter;

    @Before
    public void setUp() {

        converter = new InetSocketAddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assert.assertEquals(new InetSocketAddress("localhost", 1234), converter.convertFrom("localhost:1234"));
        Assert.assertEquals(new InetSocketAddress("127.0.0.1", 1234), converter.convertFrom("127.0.0.1:1234"));
        Assert.assertEquals(new InetSocketAddress("::1", 1234), converter.convertFrom("[::1]:1234"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalidHostname() {

        converter.convertFrom("Not a hostname#123:1234");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromPortInvalid() {

        converter.convertFrom("localhost:99999");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromPortMissing() {

        converter.convertFrom("localhost");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromHostnameMissing() {

        converter.convertFrom(":123");
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        Assert.assertEquals("localhost:1234", converter.convertTo(InetSocketAddress.createUnresolved("localhost", 1234)));
        Assert.assertEquals("127.0.0.1:1234", converter.convertTo(InetSocketAddress.createUnresolved("127.0.0.1", 1234)));
        Assert.assertEquals("[::1]:1234", converter.convertTo(InetSocketAddress.createUnresolved("[::1]", 1234)));
        Assert.assertEquals(String.format("[%s]:%d", InetAddress.getByName("::1").getHostAddress(), 1234),
                converter.convertTo(new InetSocketAddress("[::1]", 1234)));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
