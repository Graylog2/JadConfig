package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Days;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.DaysConverter}
 *
 * @author jschalanda
 */
public class DaysConverterTest {
    private DaysConverter converter;

    @Before
    public void setUp() {
        converter = new DaysConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Days.ZERO, converter.convertFrom("0"));
        assertEquals(Days.ONE, converter.convertFrom("1"));
        assertEquals(Days.days(-1), converter.convertFrom("-1"));
        assertEquals(Days.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Days.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Days.ZERO));
        assertEquals("1", converter.convertTo(Days.ONE));
        assertEquals("-1", converter.convertTo(Days.days(-1)));
        assertEquals("-2147483648", converter.convertTo(Days.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Days.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
