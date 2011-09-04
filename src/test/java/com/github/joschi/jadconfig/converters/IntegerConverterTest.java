package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link IntegerConverter}
 *
 * @author jschalanda
 */
public class IntegerConverterTest {

    IntegerConverter converter;

    @Before
    public void setUp() {

        converter = new IntegerConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(Integer.valueOf(0), converter.convert("0"));
        Assert.assertEquals(Integer.valueOf(1), converter.convert("1"));
        Assert.assertEquals(Integer.valueOf(-1), converter.convert("-1"));
        Assert.assertEquals(Integer.MIN_VALUE, converter.convert("-2147483648").intValue());
        Assert.assertEquals(Integer.MAX_VALUE, converter.convert("2147483647").intValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooBig() {

        converter.convert("2147483648");
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooSmall() {

        converter.convert("-2147483649");
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        converter.convert(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertInvalid() {

        converter.convert("Not a number");
    }
}
