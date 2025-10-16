package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntervalConverterTest {
    private IntervalConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new IntervalConverter();
    }

    @Test
    public void testConvertFrom() {
        final Interval interval = new LocalDate(2014, 1, 1).toInterval();
        assertEquals(interval, converter.convertFrom("2014-01-01T00:00:00.000/2014-01-02T00:00:00.000"));
    }

    @Test
    public void invalidSimpleFormatThrowsException() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("foo")
        );
    }

    @Test
    public void emptyStringThrowsException() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void nullThrowsException() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {
        final Interval interval = new LocalDate(2014, 1, 1).toInterval(DateTimeZone.UTC);
        assertEquals("2014-01-01T00:00:00.000Z/2014-01-02T00:00:00.000Z", converter.convertTo(interval));
    }
}