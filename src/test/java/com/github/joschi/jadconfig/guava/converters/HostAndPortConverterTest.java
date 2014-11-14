package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.HostAndPort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link HostAndPortConverter}.
 */
public class HostAndPortConverterTest {
    private HostAndPortConverter converter;

    @Before
    public void setUp() {
        converter = new HostAndPortConverter();
    }

    @Test
    public void testConvertFrom()  {
        Assert.assertEquals(HostAndPort.fromHost("localhost"), converter.convertFrom("localhost"));
        Assert.assertEquals(HostAndPort.fromParts("localhost",100), converter.convertFrom("localhost:100"));
        Assert.assertEquals(HostAndPort.fromHost("127.0.0.1"), converter.convertFrom("127.0.0.1"));
        Assert.assertEquals(HostAndPort.fromParts("127.0.0.1", 100), converter.convertFrom("127.0.0.1:100"));
        Assert.assertEquals(HostAndPort.fromHost("::1"), converter.convertFrom("::1"));
        Assert.assertEquals(HostAndPort.fromParts("[::1]", 100), converter.convertFrom("[::1]:100"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("localhost", converter.convertTo(HostAndPort.fromString("localhost")));
        Assert.assertEquals("localhost:100", converter.convertTo(HostAndPort.fromParts("localhost", 100)));
        Assert.assertEquals("127.0.0.1", converter.convertTo(HostAndPort.fromString("127.0.0.1")));
        Assert.assertEquals("127.0.0.1:100", converter.convertTo(HostAndPort.fromParts("127.0.0.1", 100)));
        Assert.assertEquals("[::1]", converter.convertTo(HostAndPort.fromString("::1")));
        Assert.assertEquals("[::1]", converter.convertTo(HostAndPort.fromString("[::1]")));
        Assert.assertEquals("[::1]:100", converter.convertTo(HostAndPort.fromParts("[::1]", 100)));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
