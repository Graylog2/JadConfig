package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.Duration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DurationConverter}
 *
 * @author jschalanda
 */
public class DurationConverterTest {
    private DurationConverter converter;

    @Before
    public void setUp() {
        converter = new DurationConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(Duration.days(1l), converter.convertFrom("1 d"));
        Assert.assertEquals(Duration.hours(2l), converter.convertFrom("2   hours"));
        Assert.assertEquals(Duration.minutes(3l), converter.convertFrom("3minutes"));
        Assert.assertEquals(Duration.seconds(4l), converter.convertFrom("4 s"));
        Assert.assertEquals(Duration.milliseconds(5l), converter.convertFrom("5 ms"));
        Assert.assertEquals(Duration.microseconds(6l), converter.convertFrom("6 microseconds"));
        Assert.assertEquals(Duration.nanoseconds(7l), converter.convertFrom("7         ns"));
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
        converter.convertFrom("Invalid Duration");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("1 hour", converter.convertTo(Duration.hours(1)));
        Assert.assertEquals("2 hours", converter.convertTo(Duration.hours(2)));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
