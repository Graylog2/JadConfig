package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link FloatConverter}
 *
 * @author jschalanda
 */
public class FloatConverterTest {

    private FloatConverter converter;

    @Before
    public void setUp() {

        converter = new FloatConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Float.valueOf(0.0f), converter.convertFrom("0.0"));
        Assert.assertEquals(Float.valueOf(-0.0f), converter.convertFrom("-0.0"));
        Assert.assertEquals(Float.valueOf(1.0f), converter.convertFrom("1.0"));
        Assert.assertEquals(Float.valueOf(-1.0f), converter.convertFrom("-1.0"));
        Assert.assertEquals(Float.valueOf(1.0f), converter.convertFrom("+1.0"));

        Assert.assertEquals(Float.MIN_VALUE, converter.convertFrom("1.4E-45"), 0.0f);
        Assert.assertEquals(Float.MAX_VALUE, converter.convertFrom("3.4028235E38"), 0.0f);

        Assert.assertEquals(0.0f, converter.convertFrom("1.4E-46"), 0.0f);
        Assert.assertEquals(Float.MIN_NORMAL, converter.convertFrom("1.1754944E-38f"), 0.0f);

        Assert.assertEquals(Float.POSITIVE_INFINITY, converter.convertFrom("3.4028235E39"), 0.0f);
        Assert.assertEquals(Float.NEGATIVE_INFINITY, converter.convertFrom("-3.4028235E39"), 0.0f);
        Assert.assertEquals(Float.POSITIVE_INFINITY, converter.convertFrom("Infinity"), 0.0f);
        Assert.assertEquals(Float.NEGATIVE_INFINITY, converter.convertFrom("-Infinity"), 0.0f);
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

        Assert.assertEquals("0.0", converter.convertTo(0.0f));
        Assert.assertEquals("-0.0", converter.convertTo(-0.0f));
        Assert.assertEquals("1.0", converter.convertTo(1.0f));
        Assert.assertEquals("-1.0", converter.convertTo(-1.0f));

        Assert.assertEquals("1.4E-45", converter.convertTo(Float.MIN_VALUE));
        Assert.assertEquals("3.4028235E38", converter.convertTo(Float.MAX_VALUE));

        Assert.assertEquals("1.1754944E-38", converter.convertTo(Float.MIN_NORMAL));

        Assert.assertEquals("Infinity", converter.convertTo(Float.POSITIVE_INFINITY));
        Assert.assertEquals("-Infinity", converter.convertTo(Float.NEGATIVE_INFINITY));
        Assert.assertEquals("NaN", converter.convertTo(Float.NaN));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
