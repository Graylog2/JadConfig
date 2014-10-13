package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit tests for {@link TimeUnitConverter}
 *
 * @author jschalanda
 */
public class TimeUnitConverterTest {
    private TimeUnitConverter converter;

    @Before
    public void setUp() {
        converter = new TimeUnitConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(TimeUnit.DAYS, converter.convertFrom("days"));
        Assert.assertEquals(TimeUnit.HOURS, converter.convertFrom("HoUrS"));
        Assert.assertEquals(TimeUnit.MINUTES, converter.convertFrom("MINUTES"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {
        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Invalid TimeUnit");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("DAYS", converter.convertTo(TimeUnit.DAYS));
        Assert.assertEquals("NANOSECONDS", converter.convertTo(TimeUnit.NANOSECONDS));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
