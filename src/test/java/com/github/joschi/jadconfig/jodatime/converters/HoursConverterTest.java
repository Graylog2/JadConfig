package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Hours;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link HoursConverter}
 *
 * @author jschalanda
 */
public class HoursConverterTest {
    private HoursConverter converter;

    @Before
    public void setUp() {
        converter = new HoursConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Hours.ZERO, converter.convertFrom("0"));
        assertEquals(Hours.ONE, converter.convertFrom("1"));
        assertEquals(Hours.hours(-1), converter.convertFrom("-1"));
        assertEquals(Hours.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Hours.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Hours.ZERO));
        assertEquals("1", converter.convertTo(Hours.ONE));
        assertEquals("-1", converter.convertTo(Hours.hours(-1)));
        assertEquals("-2147483648", converter.convertTo(Hours.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Hours.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
