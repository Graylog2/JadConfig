package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DurationConverterTest {
    private DurationConverter converter;

    @Before
    public void setUp() {
        converter = new DurationConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals("IsoFormat is parsed correctly", Duration.standardSeconds(1), converter.convertFrom("PT1S"));
        assertEquals("Complex ISO format is parsed correctly", Duration.standardSeconds(120).plus(100), converter.convertFrom("PT120.100S"));
        assertEquals("Simple format is parsed correctly", Duration.standardDays(1), converter.convertFrom("1d"));
        assertEquals("Simple format is parsed correctly", Duration.standardHours(3), converter.convertFrom("3 h"));
        assertEquals("Simple format is parsed correctly", Duration.standardMinutes(2), converter.convertFrom("2  m"));
        assertEquals("Simple format is parsed correctly", Duration.standardSeconds(4), converter.convertFrom("4S"));
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
        assertEquals("ISO format is used",
                "PT120.100S", converter.convertTo(Duration.standardMinutes(2l).plus(100)));
    }
}