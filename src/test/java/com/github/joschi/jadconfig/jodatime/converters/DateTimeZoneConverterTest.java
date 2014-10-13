package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.DateTimeZoneConverter}
 *
 * @author jschalanda
 */
public class DateTimeZoneConverterTest {
    private DateTimeZoneConverter converter;

    @Before
    public void setUp() {
        converter = new DateTimeZoneConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(DateTimeZone.forID("Europe/Berlin"), converter.convertFrom("Europe/Berlin"));
        assertEquals(DateTimeZone.forID("Asia/Tokyo"), converter.convertFrom("Asia/Tokyo"));
        assertEquals(DateTimeZone.getDefault(), converter.convertFrom(null));
        assertEquals(DateTimeZone.getDefault(), converter.convertFrom(TimeZone.getDefault().getID()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmpty() {
        converter.convertFrom("");
    }

    @Test
    public void testConvertTo() {
        assertEquals("Europe/Berlin", converter.convertTo(DateTimeZone.forID("Europe/Berlin")));
        assertEquals("Asia/Tokyo", converter.convertTo(DateTimeZone.forID("Asia/Tokyo")));
        assertEquals(TimeZone.getDefault().getID(), converter.convertTo(DateTimeZone.getDefault()));
        assertEquals(TimeZone.getDefault().getID(), converter.convertTo(null));
    }
}
