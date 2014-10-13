package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.Size;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.SizeConverter}
 *
 * @author jschalanda
 */
public class SizeConverterTest {
    private SizeConverter converter;

    @Before
    public void setUp() {
        converter = new SizeConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(Size.petabytes(1l), converter.convertFrom("1 PB"));
        Assert.assertEquals(Size.terabytes(2l), converter.convertFrom("2   T"));
        Assert.assertEquals(Size.gigabytes(3l), converter.convertFrom("3g"));
        Assert.assertEquals(Size.megabytes(4l), converter.convertFrom("4 megabytes"));
        Assert.assertEquals(Size.kilobytes(5l), converter.convertFrom("5 KiB"));
        Assert.assertEquals(Size.bytes(6l), converter.convertFrom("6 B"));
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
        converter.convertFrom("Invalid Size");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("1 byte", converter.convertTo(Size.bytes(1)));
        Assert.assertEquals("2 bytes", converter.convertTo(Size.bytes(2)));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
