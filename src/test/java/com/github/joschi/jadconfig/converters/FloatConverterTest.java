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

    FloatConverter converter;

    @Before
    public void setUp() {

        converter = new FloatConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(Float.valueOf(0.0f), converter.convert("0.0"));
        Assert.assertEquals(Float.valueOf(1.0f), converter.convert("1.0"));
        Assert.assertEquals(Float.valueOf(-1.0f), converter.convert("-1.0"));
        Assert.assertEquals(Float.valueOf(1.0f), converter.convert("+1.0"));

        Assert.assertEquals(Float.MIN_VALUE, converter.convert("1.4E-45"), 0.0f);
        Assert.assertEquals(Float.MAX_VALUE, converter.convert("3.4028235E38"), 0.0f);

        Assert.assertEquals(0.0f, converter.convert("1.4E-46"), 0.0f);
        Assert.assertEquals(Float.MIN_NORMAL, converter.convert("1.17549435E-38f"), 0.0f);

        Assert.assertTrue(converter.convert("3.4028235E39").isInfinite());
        Assert.assertTrue(converter.convert("-3.4028235E39").isInfinite());
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
