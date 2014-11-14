package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.HostSpecifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link HostSpecifierConverter}.
 */
public class HostSpecifierConverterTest {
    private HostSpecifierConverter converter;

    @Before
    public void setUp() {
        converter = new HostSpecifierConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(HostSpecifier.fromValid("joschi.github.io"), converter.convertFrom("joschi.github.io"));
        Assert.assertEquals(HostSpecifier.fromValid("127.0.0.1"), converter.convertFrom("127.0.0.1"));
        Assert.assertEquals(HostSpecifier.fromValid("::1"), converter.convertFrom("::1"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }


    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Not a host specifier#123");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("joschi.github.io", converter.convertTo(HostSpecifier.fromValid("joschi.github.io")));
        Assert.assertEquals("127.0.0.1", converter.convertTo(HostSpecifier.fromValid("127.0.0.1")));
        Assert.assertEquals("[::1]", converter.convertTo(HostSpecifier.fromValid("::1")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
