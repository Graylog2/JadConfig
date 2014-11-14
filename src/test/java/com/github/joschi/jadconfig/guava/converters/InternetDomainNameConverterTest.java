package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.InternetDomainName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link InternetDomainNameConverter}.
 */
public class InternetDomainNameConverterTest {
    private InternetDomainNameConverter converter;

    @Before
    public void setUp() {
        converter = new InternetDomainNameConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(InternetDomainName.from("com"), converter.convertFrom("com"));
        Assert.assertEquals(InternetDomainName.from("foo.co.uk"), converter.convertFrom("foo.co.uk"));
        Assert.assertEquals(InternetDomainName.from("joschi.github.io"), converter.convertFrom("joschi.github.io"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }


    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Not an Internet domain name#123");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("com", converter.convertTo(InternetDomainName.from("com")));
        Assert.assertEquals("foo.co.uk", converter.convertTo(InternetDomainName.from("foo.co.uk")));
        Assert.assertEquals("joschi.github.io", converter.convertTo(InternetDomainName.from("joschi.github.io")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
