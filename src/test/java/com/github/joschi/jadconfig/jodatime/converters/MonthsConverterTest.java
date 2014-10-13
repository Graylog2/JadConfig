package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Months;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.MonthsConverter}
 *
 * @author jschalanda
 */
public class MonthsConverterTest {
    private MonthsConverter converter;

    @Before
    public void setUp() {
        converter = new MonthsConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Months.ZERO, converter.convertFrom("0"));
        assertEquals(Months.ONE, converter.convertFrom("1"));
        assertEquals(Months.months(-1), converter.convertFrom("-1"));
        assertEquals(Months.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Months.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Months.ZERO));
        assertEquals("1", converter.convertTo(Months.ONE));
        assertEquals("-1", converter.convertTo(Months.months(-1)));
        assertEquals("-2147483648", converter.convertTo(Months.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Months.MAX_VALUE));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
