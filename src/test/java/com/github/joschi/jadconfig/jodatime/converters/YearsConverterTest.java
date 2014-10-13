package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Years;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link YearsConverter}
 *
 * @author jschalanda
 */
public class YearsConverterTest {
    private YearsConverter converter;

    @Before
    public void setUp() {
        converter = new YearsConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Years.ZERO, converter.convertFrom("0"));
        assertEquals(Years.ONE, converter.convertFrom("1"));
        assertEquals(Years.years(-1), converter.convertFrom("-1"));
        assertEquals(Years.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Years.MAX_VALUE, converter.convertFrom("2147483647"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooBig() {
        converter.convertFrom("2147483648");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromTooSmall() {
        converter.convertFrom("-2147483649");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmpty() {
        converter.convertFrom("");
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
        assertEquals("0", converter.convertTo(Years.ZERO));
        assertEquals("1", converter.convertTo(Years.ONE));
        assertEquals("-1", converter.convertTo(Years.years(-1)));
        assertEquals("-2147483648", converter.convertTo(Years.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Years.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
