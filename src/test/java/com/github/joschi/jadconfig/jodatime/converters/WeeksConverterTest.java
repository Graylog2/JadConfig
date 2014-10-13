package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Weeks;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link WeeksConverter}
 *
 * @author jschalanda
 */
public class WeeksConverterTest {
    private WeeksConverter converter;

    @Before
    public void setUp() {
        converter = new WeeksConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Weeks.ZERO, converter.convertFrom("0"));
        assertEquals(Weeks.ONE, converter.convertFrom("1"));
        assertEquals(Weeks.weeks(-1), converter.convertFrom("-1"));
        assertEquals(Weeks.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Weeks.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Weeks.ZERO));
        assertEquals("1", converter.convertTo(Weeks.ONE));
        assertEquals("-1", converter.convertTo(Weeks.weeks(-1)));
        assertEquals("-2147483648", converter.convertTo(Weeks.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Weeks.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
