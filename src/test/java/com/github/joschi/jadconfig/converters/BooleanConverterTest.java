package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link BooleanConverter}
 *
 * @author jschalanda
 */
public class BooleanConverterTest {

    private BooleanConverter converter;

    @Before
    public void setUp() {

        converter = new BooleanConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertTrue(converter.convertFrom("true"));
        Assert.assertTrue(converter.convertFrom("tRuE"));
        Assert.assertFalse(converter.convertFrom("false"));
        Assert.assertFalse(converter.convertFrom("fAlSe"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        Assert.assertTrue(converter.convertFrom(null));
    }

    @Test(expected = ParameterException.class)
    public void testConvertEmpty() {

        Assert.assertTrue(converter.convertFrom(""));
    }

    @Test(expected = ParameterException.class)
    public void testConvertInvalid() {

        Assert.assertTrue(converter.convertFrom("yes"));
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("true", converter.convertTo(true));
        Assert.assertEquals("false", converter.convertTo(false));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
    
    @Test
    public void testConvertFromParameterWithTrailingOrLeadingWhitespaces() {
        Assert.assertTrue(converter.convertFrom(" true"));
        Assert.assertFalse(converter.convertFrom("false "));
    }
}
