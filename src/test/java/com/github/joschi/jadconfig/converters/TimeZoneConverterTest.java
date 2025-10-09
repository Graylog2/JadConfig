package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.TimeZoneConverter}
 *
 * @author jschalanda
 */
public class TimeZoneConverterTest {

    private TimeZoneConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new TimeZoneConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(TimeZone.getTimeZone("GMT"), converter.convertFrom(""));
        Assertions.assertEquals(TimeZone.getTimeZone("Europe/Berlin"), converter.convertFrom("Europe/Berlin"));
        Assertions.assertEquals(TimeZone.getTimeZone("Asia/Tokyo"), converter.convertFrom("Asia/Tokyo"));
        Assertions.assertEquals(TimeZone.getDefault(), converter.convertFrom(TimeZone.getDefault().getID()));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("GMT", converter.convertTo(TimeZone.getTimeZone("")));
        Assertions.assertEquals("Europe/Berlin", converter.convertTo(TimeZone.getTimeZone("Europe/Berlin")));
        Assertions.assertEquals("Asia/Tokyo", converter.convertTo(TimeZone.getTimeZone("Asia/Tokyo")));
        Assertions.assertEquals(TimeZone.getDefault().getID(), converter.convertTo(TimeZone.getDefault()));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
