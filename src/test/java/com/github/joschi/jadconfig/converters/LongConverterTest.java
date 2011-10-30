package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link LongConverter}
 *
 * @author jschalanda
 */
public class LongConverterTest {

    private LongConverter converter;

    @Before
    public void setUp() {

        converter = new LongConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Long.valueOf(0L), converter.convertFrom("0"));
        Assert.assertEquals(Long.valueOf(1L), converter.convertFrom("1"));
        Assert.assertEquals(Long.valueOf(-1L), converter.convertFrom("-1"));
        Assert.assertEquals(Long.MIN_VALUE, converter.convertFrom("-9223372036854775808").longValue());
        Assert.assertEquals(Long.MAX_VALUE, converter.convertFrom("9223372036854775807").longValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {

        converter.convertFrom("9223372036854775808");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooSmall() {

        converter.convertFrom("-9223372036854775809");
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

        Assert.assertEquals("0", converter.convertTo(0L));
        Assert.assertEquals("1", converter.convertTo(1L));
        Assert.assertEquals("-1", converter.convertTo(-1L));
        Assert.assertEquals("-9223372036854775808", converter.convertTo(Long.MIN_VALUE));
        Assert.assertEquals("9223372036854775807", converter.convertTo(Long.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
        
    @Test
    public void testConvertFromParameterWithTrailingOrLeadingWhitespaces() {
        Assert.assertEquals(Long.valueOf(0L), converter.convertFrom(" 0"));
        Assert.assertEquals(Long.valueOf(1L), converter.convertFrom("1 "));
    }
}
