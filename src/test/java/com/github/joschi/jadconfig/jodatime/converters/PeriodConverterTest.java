package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PeriodConverterTest {
    private PeriodConverter converter;

    @Before
    public void setUp() {
        converter = new PeriodConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals("IsoFormat is parsed correctly", Period.minutes(1), converter.convertFrom("PT1M"));
        assertEquals("Complex IsoFormat is parsed correctly", Period.days(4).withMinutes(1), converter.convertFrom("P4DT1M"));
        assertEquals("Simple format is parsed correctly", Period.years(1), converter.convertFrom("1 year"));
        assertEquals("Simple format is parsed correctly", Period.months(6), converter.convertFrom("6 months"));
        assertEquals("Simple format is parsed correctly", Period.weeks(5), converter.convertFrom("5 weeks"));
        assertEquals("Simple format is parsed correctly", Period.days(1), converter.convertFrom("1d"));
        assertEquals("Simple format is parsed correctly", Period.hours(3), converter.convertFrom("3 h"));
        assertEquals("Simple format is parsed correctly", Period.minutes(2), converter.convertFrom("2  m"));
        assertEquals("Simple format is parsed correctly", Period.seconds(4), converter.convertFrom("4S"));
    }

    @Test(expected = ParameterException.class)
    public void invalidSimpleFormatThrowsException() {
        converter.convertFrom("1foo");
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
        assertEquals("IsoDateFormatter is used",
                "P1DT4H1M", converter.convertTo(Period.days(1).withHours(4).withMinutes(1)));
    }
}