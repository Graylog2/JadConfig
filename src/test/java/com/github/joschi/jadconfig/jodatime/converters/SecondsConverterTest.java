package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Seconds;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.SecondsConverter}
 *
 * @author jschalanda
 */
public class SecondsConverterTest {
    private SecondsConverter converter;

    @Before
    public void setUp() {
        converter = new SecondsConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Seconds.ZERO, converter.convertFrom("0"));
        assertEquals(Seconds.ONE, converter.convertFrom("1"));
        assertEquals(Seconds.seconds(-1), converter.convertFrom("-1"));
        assertEquals(Seconds.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Seconds.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Seconds.ZERO));
        assertEquals("1", converter.convertTo(Seconds.ONE));
        assertEquals("-1", converter.convertTo(Seconds.seconds(-1)));
        assertEquals("-2147483648", converter.convertTo(Seconds.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Seconds.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
