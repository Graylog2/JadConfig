package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
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
    public void testConvert() {

        Assert.assertEquals(Short.valueOf((short) 0), converter.convert("0"));
        Assert.assertEquals(Short.valueOf((short) 1), converter.convert("1"));
        Assert.assertEquals(Short.valueOf((short) -1), converter.convert("-1"));
        Assert.assertEquals(Short.MIN_VALUE, converter.convert("-32768").shortValue());
        Assert.assertEquals(Short.MAX_VALUE, converter.convert("32767").shortValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooBig() {

        converter.convert("32768");
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooSmall() {

        converter.convert("-32769");
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
