package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Unit tests for {@link DecimalFormatConverter}
 *
 * @author jschalanda
 */
public class DecimalFormatConverterTest {

    private DecimalFormatConverter converter;

    @Before
    public void setUp() {

        converter = new DecimalFormatConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(new DecimalFormat("0.###E0"), converter.convertFrom("0.###E0"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("0.###E0", converter.convertTo(new DecimalFormat("0.###E0")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
