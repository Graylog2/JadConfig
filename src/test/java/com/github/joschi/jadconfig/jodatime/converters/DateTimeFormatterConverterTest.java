package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.DateTimeFormatterConverter}
 *
 * @author jschalanda
 */
public class DateTimeFormatterConverterTest {
    private DateTimeFormatterConverter converter;

    @Before
    public void setUp() {
        converter = new DateTimeFormatterConverter();
    }

    @Test
    public void testConvertFrom() {
        final DateTime dateTime = new LocalDate(2014, 1, 1).toDateTimeAtStartOfDay(DateTimeZone.UTC);
        assertEquals("2014-01-01", converter.convertFrom("yyyy-MM-dd").print(dateTime));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmpty() {
        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalidPattern() {
        converter.convertFrom("Invalid Pattern");
    }

    @Test
    public void testConvertTo() {
        assertEquals("", converter.convertTo(DateTimeFormat.fullDate()));
        assertEquals("", converter.convertTo(DateTimeFormat.shortDate()));
        assertEquals("", converter.convertTo(DateTimeFormat.mediumDateTime()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
