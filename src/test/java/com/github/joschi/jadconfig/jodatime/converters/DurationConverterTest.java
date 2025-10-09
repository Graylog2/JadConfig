package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DurationConverterTest {
    private DurationConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new DurationConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Duration.standardSeconds(1), converter.convertFrom("PT1S"), "IsoFormat is parsed correctly");
        assertEquals(Duration.standardSeconds(120).plus(100), converter.convertFrom("PT120.100S"), "Complex ISO format is parsed correctly");
        assertEquals(Duration.standardDays(1), converter.convertFrom("1d"), "Simple format is parsed correctly");
        assertEquals(Duration.standardHours(3), converter.convertFrom("3 h"), "Simple format is parsed correctly");
        assertEquals(Duration.standardMinutes(2), converter.convertFrom("2  m"), "Simple format is parsed correctly");
        assertEquals(Duration.standardSeconds(4), converter.convertFrom("4S"), "Simple format is parsed correctly");
    }

    @Test
    public void invalidSimpleFormatThrowsException() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("1foo")
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
        assertEquals("PT120.100S", converter.convertTo(Duration.standardMinutes(2l).plus(100)), "ISO format is used");
    }
}