package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link DurationConverter}
 *
 * @author jschalanda
 */
public class DurationConverterTest {
    private DurationConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new DurationConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(Duration.days(1l), converter.convertFrom("1 d"));
        Assertions.assertEquals(Duration.hours(2l), converter.convertFrom("2   hours"));
        Assertions.assertEquals(Duration.minutes(3l), converter.convertFrom("3minutes"));
        Assertions.assertEquals(Duration.seconds(4l), converter.convertFrom("4 s"));
        Assertions.assertEquals(Duration.milliseconds(5l), converter.convertFrom("5 ms"));
        Assertions.assertEquals(Duration.microseconds(6l), converter.convertFrom("6 microseconds"));
        Assertions.assertEquals(Duration.nanoseconds(7l), converter.convertFrom("7         ns"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromEmptyString() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Invalid Duration")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("1 hour", converter.convertTo(Duration.hours(1)));
        Assertions.assertEquals("2 hours", converter.convertTo(Duration.hours(2)));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
