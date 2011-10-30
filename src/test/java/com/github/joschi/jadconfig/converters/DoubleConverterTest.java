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

    private DoubleConverter converter;

    @Before
    public void setUp() {

        converter = new DoubleConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Double.valueOf(0.0d), converter.convertFrom("0.0"));
        Assert.assertEquals(Double.valueOf(-0.0d), converter.convertFrom("-0.0"));
        Assert.assertEquals(Double.valueOf(1.0d), converter.convertFrom("1.0"));
        Assert.assertEquals(Double.valueOf(-1.0d), converter.convertFrom("-1.0"));
        Assert.assertEquals(Double.valueOf(1.0d), converter.convertFrom("+1.0"));

        Assert.assertEquals(Double.MIN_VALUE, converter.convertFrom("4.9E-324"), 0.0d);
        Assert.assertEquals(Double.MAX_VALUE, converter.convertFrom("1.7976931348623157E308"), 0.0d);

        Assert.assertEquals(0.0d, converter.convertFrom("4.9E-325"), 0.0d);
        Assert.assertEquals(Double.MIN_NORMAL, converter.convertFrom("2.2250738585072014E-308"), 0.0d);

        Assert.assertEquals(Double.POSITIVE_INFINITY, converter.convertFrom("Infinity"), 0.0d);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, converter.convertFrom("-Infinity"), 0.0d);
        Assert.assertEquals(Double.POSITIVE_INFINITY, converter.convertFrom("1.7976931348623157E309"), 0.0d);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, converter.convertFrom("-1.7976931348623157E309"), 0.0d);
        Assert.assertTrue(converter.convertFrom("NaN").isNaN());
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

        Assert.assertEquals("0.0", converter.convertTo(0.0d));
        Assert.assertEquals("-0.0", converter.convertTo(-0.0d));
        Assert.assertEquals("1.0", converter.convertTo(1.0d));
        Assert.assertEquals("-1.0", converter.convertTo(-1.0d));

        Assert.assertEquals("4.9E-324", converter.convertTo(Double.MIN_VALUE));
        Assert.assertEquals("1.7976931348623157E308", converter.convertTo(Double.MAX_VALUE));
        Assert.assertEquals("2.2250738585072014E-308", converter.convertTo(Double.MIN_NORMAL));

        Assert.assertEquals("Infinity", converter.convertTo(Double.POSITIVE_INFINITY));
        Assert.assertEquals("-Infinity", converter.convertTo(Double.NEGATIVE_INFINITY));
        Assert.assertEquals("NaN", converter.convertTo(Double.NaN));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
    
    @Test
    public void testConvertFromParameterWithTrailingOrLeadingWhitespaces() {
        Assert.assertEquals(Double.valueOf(0.0d), converter.convertFrom(" 0.0"));
        Assert.assertEquals(Double.valueOf(1.0d), converter.convertFrom("1.0 "));
    }
}
