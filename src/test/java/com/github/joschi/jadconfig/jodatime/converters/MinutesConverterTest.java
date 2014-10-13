package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Minutes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link MinutesConverter}
 *
 * @author jschalanda
 */
public class MinutesConverterTest {
    private MinutesConverter converter;

    @Before
    public void setUp() {
        converter = new MinutesConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Minutes.ZERO, converter.convertFrom("0"));
        assertEquals(Minutes.ONE, converter.convertFrom("1"));
        assertEquals(Minutes.minutes(-1), converter.convertFrom("-1"));
        assertEquals(Minutes.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Minutes.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Minutes.ZERO));
        assertEquals("1", converter.convertTo(Minutes.ONE));
        assertEquals("-1", converter.convertTo(Minutes.minutes(-1)));
        assertEquals("-2147483648", converter.convertTo(Minutes.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Minutes.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
