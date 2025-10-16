package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Period;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PeriodConverterTest {
    private PeriodConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new PeriodConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Period.minutes(1), converter.convertFrom("PT1M"), "IsoFormat is parsed correctly");
        assertEquals(Period.days(4).withMinutes(1), converter.convertFrom("P4DT1M"), "Complex IsoFormat is parsed correctly");
        assertEquals(Period.years(1), converter.convertFrom("1 year"), "Simple format is parsed correctly");
        assertEquals(Period.months(6), converter.convertFrom("6 months"), "Simple format is parsed correctly");
        assertEquals(Period.weeks(5), converter.convertFrom("5 weeks"), "Simple format is parsed correctly");
        assertEquals(Period.days(1), converter.convertFrom("1d"), "Simple format is parsed correctly");
        assertEquals(Period.hours(3), converter.convertFrom("3 h"), "Simple format is parsed correctly");
        assertEquals(Period.minutes(2), converter.convertFrom("2  m"), "Simple format is parsed correctly");
        assertEquals(Period.seconds(4), converter.convertFrom("4S"), "Simple format is parsed correctly");
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
        assertEquals("P1DT4H1M", converter.convertTo(Period.days(1).withHours(4).withMinutes(1)), "IsoDateFormatter is used");
    }
}