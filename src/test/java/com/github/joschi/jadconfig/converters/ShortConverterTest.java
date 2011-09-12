package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ShortConverter}
 *
 * @author jschalanda
 */
public class ShortConverterTest {

    ShortConverter converter;

    @Before
    public void setUp() {

        converter = new ShortConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Short.valueOf((short) 0), converter.convertFrom("0"));
        Assert.assertEquals(Short.valueOf((short) 1), converter.convertFrom("1"));
        Assert.assertEquals(Short.valueOf((short) -1), converter.convertFrom("-1"));
        Assert.assertEquals(Short.MIN_VALUE, converter.convertFrom("-32768").shortValue());
        Assert.assertEquals(Short.MAX_VALUE, converter.convertFrom("32767").shortValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {

        converter.convertFrom("32768");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooSmall() {

        converter.convertFrom("-32769");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Not a number");
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("0", converter.convertTo((short) 0));
        Assert.assertEquals("1", converter.convertTo((short) 1));
        Assert.assertEquals("-1", converter.convertTo((short) -1));
        Assert.assertEquals("-32768", converter.convertTo(Short.MIN_VALUE));
        Assert.assertEquals("32767", converter.convertTo(Short.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
