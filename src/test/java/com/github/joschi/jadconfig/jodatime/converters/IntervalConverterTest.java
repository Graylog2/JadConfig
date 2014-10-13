package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntervalConverterTest {
    private IntervalConverter converter;

    @Before
    public void setUp() {
        converter = new IntervalConverter();
    }

    @Test
    public void testConvertFrom() {
        final Interval interval = new LocalDate(2014, 1, 1).toInterval();
        assertEquals(interval, converter.convertFrom("2014-01-01T00:00:00.000/2014-01-02T00:00:00.000"));
    }

    @Test(expected = ParameterException.class)
    public void invalidSimpleFormatThrowsException() {
        converter.convertFrom("foo");
    }

    @Test(expected = ParameterException.class)
    public void emptyStringThrowsException() {
        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void nullThrowsException() {
        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {
        final Interval interval = new LocalDate(2014, 1, 1).toInterval(DateTimeZone.UTC);
        assertEquals("2014-01-01T00:00:00.000Z/2014-01-02T00:00:00.000Z", converter.convertTo(interval));
    }
}