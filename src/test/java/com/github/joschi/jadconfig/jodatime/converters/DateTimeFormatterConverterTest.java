package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.DateTimeFormatterConverter}
 *
 * @author jschalanda
 */
public class DateTimeFormatterConverterTest {
    private DateTimeFormatterConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new DateTimeFormatterConverter();
    }

    @Test
    public void testConvertFrom() {
        final DateTime dateTime = new LocalDate(2014, 1, 1).toDateTimeAtStartOfDay(DateTimeZone.UTC);
        assertEquals("2014-01-01", converter.convertFrom("yyyy-MM-dd").print(dateTime));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromEmpty() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromInvalidPattern() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("Invalid Pattern")
        );
    }

    @Test
    public void testConvertTo() {
        assertEquals("", converter.convertTo(DateTimeFormat.fullDate()));
        assertEquals("", converter.convertTo(DateTimeFormat.shortDate()));
        assertEquals("", converter.convertTo(DateTimeFormat.mediumDateTime()));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
