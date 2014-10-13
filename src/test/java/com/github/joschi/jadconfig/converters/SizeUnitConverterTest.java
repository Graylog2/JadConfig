package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.SizeUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link SizeUnitConverter}
 *
 * @author jschalanda
 */
public class SizeUnitConverterTest {
    private SizeUnitConverter converter;

    @Before
    public void setUp() {
        converter = new SizeUnitConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(SizeUnit.BYTES, converter.convertFrom("bytes"));
        Assert.assertEquals(SizeUnit.KILOBYTES, converter.convertFrom("KILObytes"));
        Assert.assertEquals(SizeUnit.MEGABYTES, converter.convertFrom("MEGABYTES"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {
        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Invalid SizeUnit");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("BYTES", converter.convertTo(SizeUnit.BYTES));
        Assert.assertEquals("KILOBYTES", converter.convertTo(SizeUnit.KILOBYTES));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
