package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.TimeZone;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.TimeZoneConverter}
 *
 * @author jschalanda
 */
public class TimeZoneConverterTest {

    private TimeZoneConverter converter;

    @Before
    public void setUp() {

        converter = new TimeZoneConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(TimeZone.getTimeZone("GMT"), converter.convertFrom(""));
        Assert.assertEquals(TimeZone.getTimeZone("Europe/Berlin"), converter.convertFrom("Europe/Berlin"));
        Assert.assertEquals(TimeZone.getTimeZone("Asia/Tokyo"), converter.convertFrom("Asia/Tokyo"));
        Assert.assertEquals(TimeZone.getDefault(), converter.convertFrom(TimeZone.getDefault().getID()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("GMT", converter.convertTo(TimeZone.getTimeZone("")));
        Assert.assertEquals("Europe/Berlin", converter.convertTo(TimeZone.getTimeZone("Europe/Berlin")));
        Assert.assertEquals("Asia/Tokyo", converter.convertTo(TimeZone.getTimeZone("Asia/Tokyo")));
        Assert.assertEquals(TimeZone.getDefault().getID(), converter.convertTo(TimeZone.getDefault()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
