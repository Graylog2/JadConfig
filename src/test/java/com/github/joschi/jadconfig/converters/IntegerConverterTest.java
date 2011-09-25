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

    private IntegerConverter converter;

    @Before
    public void setUp() {

        converter = new IntegerConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Integer.valueOf(0), converter.convertFrom("0"));
        Assert.assertEquals(Integer.valueOf(1), converter.convertFrom("1"));
        Assert.assertEquals(Integer.valueOf(-1), converter.convertFrom("-1"));
        Assert.assertEquals(Integer.MIN_VALUE, converter.convertFrom("-2147483648").intValue());
        Assert.assertEquals(Integer.MAX_VALUE, converter.convertFrom("2147483647").intValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {

        converter.convertFrom("2147483648");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooSmall() {

        converter.convertFrom("-2147483649");
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

        Assert.assertEquals("0", converter.convertTo(0));
        Assert.assertEquals("1", converter.convertTo(1));
        Assert.assertEquals("-1", converter.convertTo(-1));
        Assert.assertEquals("-2147483648", converter.convertTo(Integer.MIN_VALUE));
        Assert.assertEquals("2147483647", converter.convertTo(Integer.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
