package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.MediaType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link MediaTypeConverter}.
 */
public class MediaTypeConverterTest {
    private MediaTypeConverter converter;

    @Before
    public void setUp() {
        converter = new MediaTypeConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(MediaType.ANY_TYPE, converter.convertFrom("*/*"));
        Assert.assertEquals(MediaType.create("text", "html"), converter.convertFrom("text/html"));
        Assert.assertEquals(MediaType.JSON_UTF_8, converter.convertFrom("application/json; charset=utf-8"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }


    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Not a media type#123");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("*/*", converter.convertTo(MediaType.ANY_TYPE));
        Assert.assertEquals("text/html", converter.convertTo(MediaType.create("text", "html")));
        Assert.assertEquals("application/json; charset=utf-8", converter.convertTo(MediaType.JSON_UTF_8));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
