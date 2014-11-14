package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.primitives.UnsignedLong;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link UnsignedLongConverter}
 */
public class UnsignedLongConverterTest {
    private UnsignedLongConverter converter;

    @Before
    public void setUp() {
        converter = new UnsignedLongConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(UnsignedLong.ZERO, converter.convertFrom("0"));
        Assert.assertEquals(UnsignedLong.ONE, converter.convertFrom("1"));
        Assert.assertEquals(UnsignedLong.valueOf(Long.MAX_VALUE), converter.convertFrom("9223372036854775807"));
        Assert.assertEquals(UnsignedLong.MAX_VALUE, converter.convertFrom("18446744073709551615"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {
        converter.convertFrom("18446744073709551616");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNegative() {
        converter.convertFrom("-1");
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
        Assert.assertEquals("0", converter.convertTo(UnsignedLong.ZERO));
        Assert.assertEquals("1", converter.convertTo(UnsignedLong.ONE));
        Assert.assertEquals("18446744073709551615", converter.convertTo(UnsignedLong.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
