package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DoubleConverter}
 *
 * @author jschalanda
 */
public class DoubleConverterTest {

    DoubleConverter converter;

    @Before
    public void setUp() {

        converter = new DoubleConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(Double.valueOf(0.0f), converter.convert("0.0"));
        Assert.assertEquals(Double.valueOf(1.0f), converter.convert("1.0"));
        Assert.assertEquals(Double.valueOf(-1.0f), converter.convert("-1.0"));
        Assert.assertEquals(Double.valueOf(1.0f), converter.convert("+1.0"));

        Assert.assertEquals(Double.MIN_VALUE, converter.convert("4.9E-324"), 0.0d);
        Assert.assertEquals(Double.MAX_VALUE, converter.convert("1.7976931348623157E308"), 0.0d);

        Assert.assertEquals(0.0d, converter.convert("4.9E-325"), 0.0d);
        Assert.assertEquals(Double.MIN_NORMAL, converter.convert("2.2250738585072014E-308"), 0.0d);

        Assert.assertTrue(converter.convert("1.7976931348623157E309").isInfinite());
        Assert.assertTrue(converter.convert("-1.7976931348623157E309").isInfinite());
        Assert.assertTrue(converter.convert("NaN").isNaN());
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
