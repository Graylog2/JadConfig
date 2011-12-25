package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Unit tests for {@link URLConverter}
 *
 * @author jschalanda
 */
public class URLConverterTest {

    private URLConverter converter;

    @Before
    public void setUp() {

        converter = new URLConverter();
    }

    @Test
    public void testConvertFrom() throws MalformedURLException {

        Assert.assertEquals(new URL("http://localhost:80/path?key=value#fragment"), converter.convertFrom("http://localhost:80/path?key=value#fragment"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {

        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Not an URL");
    }

    @Test
    public void testConvertTo() throws MalformedURLException {

        Assert.assertEquals("http://localhost:80/path?key=value#fragment", converter.convertTo(new URL("http://localhost:80/path?key=value#fragment")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
