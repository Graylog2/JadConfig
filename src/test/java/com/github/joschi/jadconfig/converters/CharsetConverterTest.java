package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Unit tests for {@link CharsetConverter}
 *
 * @author jschalanda
 */
public class CharsetConverterTest {

    private CharsetConverter converter;

    @Before
    public void setUp() {

        converter = new CharsetConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Charset.forName("UTF-8"), converter.convertFrom("UTF-8"));
        Assert.assertEquals(Charset.forName("ISO-8859-1"), converter.convertFrom("ISO-8859-1"));
        Assert.assertEquals(Charset.defaultCharset(), converter.convertFrom(Charset.defaultCharset().name()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {

        converter.convertFrom("");
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("UTF-8", converter.convertTo(Charset.forName("UTF-8")));
        Assert.assertEquals("ISO-8859-1", converter.convertTo(Charset.forName("ISO-8859-1")));
        Assert.assertEquals(Charset.defaultCharset().name(), converter.convertTo(Charset.defaultCharset()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
