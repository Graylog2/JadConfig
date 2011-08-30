package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link BooleanConverter}
 *
 * @author jschalanda
 */
public class BooleanConverterTest {

    BooleanConverter converter;

    @Before
    public void setUp() {

        converter = new BooleanConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertTrue(converter.convert("true"));
        Assert.assertTrue(converter.convert("tRuE"));
        Assert.assertFalse(converter.convert("false"));
        Assert.assertFalse(converter.convert("fAlSe"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        Assert.assertTrue(converter.convert(null));
    }

    @Test(expected = ParameterException.class)
    public void testConvertEmpty() {

        Assert.assertTrue(converter.convert(""));
    }

    @Test(expected = ParameterException.class)
    public void testConvertInvalid() {

        Assert.assertTrue(converter.convert("yes"));
    }
}
