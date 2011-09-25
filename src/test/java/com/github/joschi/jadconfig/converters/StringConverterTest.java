package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link StringConverter}
 *
 * @author jschalanda
 */
public class StringConverterTest {

    private StringConverter converter;

    @Before
    public void setUp() {

        converter = new StringConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals("", converter.convertFrom(""));
        Assert.assertEquals("Test", converter.convertFrom("Test"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("", converter.convertTo(""));
        Assert.assertEquals("Test", converter.convertTo("Test"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
