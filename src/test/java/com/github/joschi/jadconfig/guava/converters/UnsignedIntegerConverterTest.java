package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.primitives.UnsignedInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link UnsignedIntegerConverter}
 */
public class UnsignedIntegerConverterTest {
    private UnsignedIntegerConverter converter;

    @Before
    public void setUp() {
        converter = new UnsignedIntegerConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(UnsignedInteger.ZERO, converter.convertFrom("0"));
        Assert.assertEquals(UnsignedInteger.ONE, converter.convertFrom("1"));
        Assert.assertEquals(UnsignedInteger.valueOf(Integer.MAX_VALUE), converter.convertFrom("2147483647"));
        Assert.assertEquals(UnsignedInteger.MAX_VALUE, converter.convertFrom("4294967295"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {
        converter.convertFrom("4294967296");
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
    public void testConvertInvalid() {
        converter.convertFrom("Not a number");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("0", converter.convertTo(UnsignedInteger.ZERO));
        Assert.assertEquals("1", converter.convertTo(UnsignedInteger.ONE));
        Assert.assertEquals("2147483647", converter.convertTo(UnsignedInteger.valueOf(Integer.MAX_VALUE)));
        Assert.assertEquals("4294967295", converter.convertTo(UnsignedInteger.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
