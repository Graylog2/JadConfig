package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ByteConverter}
 *
 * @author jschalanda
 */
public class ByteConverterTest {

    private ByteConverter converter;

    @Before
    public void setUp() {

        converter = new ByteConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Byte.valueOf((byte) 0), converter.convertFrom("0"));
        Assert.assertEquals(Byte.valueOf((byte) 1), converter.convertFrom("1"));
        Assert.assertEquals(Byte.valueOf((byte) -1), converter.convertFrom("-1"));
        Assert.assertEquals(Byte.MIN_VALUE, converter.convertFrom("-128").byteValue());
        Assert.assertEquals(Byte.MAX_VALUE, converter.convertFrom("127").byteValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {

        converter.convertFrom("128");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooSmall() {

        converter.convertFrom("-129");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertInvalid() {

        converter.convertFrom("Not a number");
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("0", converter.convertTo((byte) 0));
        Assert.assertEquals("1", converter.convertTo((byte) 1));
        Assert.assertEquals("-1", converter.convertTo((byte) -1));
        Assert.assertEquals("-128", converter.convertTo(Byte.MIN_VALUE));
        Assert.assertEquals("127", converter.convertTo(Byte.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }

    @Test
    public void testConvertFromParameterWithTrailingOrLeadingWhitespaces() {
        Assert.assertEquals(Byte.valueOf((byte) 1), converter.convertFrom("1 "));
        Assert.assertEquals(Byte.valueOf((byte) -1), converter.convertFrom(" -1"));
    }

}
