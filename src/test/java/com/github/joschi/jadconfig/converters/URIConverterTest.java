package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * Unit tests for {@link URIConverter}
 *
 * @author jschalanda
 */
public class URIConverterTest {

    private URIConverter converter;

    @Before
    public void setUp() {

        converter = new URIConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(URI.create("http://localhost:80/path?key=value#fragment"), converter.convertFrom("http://localhost:80/path?key=value#fragment"));
        Assert.assertEquals(URI.create(""), converter.convertFrom(""));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Not an URI");
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("http://localhost:80/path?key=value#fragment", converter.convertTo(URI.create("http://localhost:80/path?key=value#fragment")));
        Assert.assertEquals("", converter.convertTo(URI.create("")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
