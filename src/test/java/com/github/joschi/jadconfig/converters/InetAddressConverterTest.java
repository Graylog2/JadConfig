package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Unit tests for {@link InetAddressConverter}
 *
 * @author jschalanda
 */
public class InetAddressConverterTest {

    private InetAddressConverter converter;

    @Before
    public void setUp() {

        converter = new InetAddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assert.assertEquals(InetAddress.getByName("localhost"), converter.convertFrom("localhost"));
        Assert.assertEquals(InetAddress.getByName("127.0.0.1"), converter.convertFrom("127.0.0.1"));
        Assert.assertEquals(InetAddress.getByName("::1"), converter.convertFrom("::1"));
        Assert.assertEquals(InetAddress.getByName("example.com"), converter.convertFrom("example.com"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Not a hostname#123");
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        String loopbackCanonicalHostName = InetAddress.getLocalHost().getCanonicalHostName();

        Assert.assertEquals(InetAddress.getByName("example.com").getCanonicalHostName(), converter.convertTo(InetAddress.getByName("example.com")));
        Assert.assertEquals(loopbackCanonicalHostName, converter.convertTo(InetAddress.getLocalHost()));
        Assert.assertEquals(InetAddress.getByName("").getCanonicalHostName(), converter.convertTo(InetAddress.getByName("")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
