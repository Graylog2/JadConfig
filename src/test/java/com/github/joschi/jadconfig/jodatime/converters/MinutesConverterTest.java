package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Minutes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link MinutesConverter}
 *
 * @author jschalanda
 */
public class MinutesConverterTest {
    private MinutesConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new MinutesConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Minutes.ZERO, converter.convertFrom("0"));
        assertEquals(Minutes.ONE, converter.convertFrom("1"));
        assertEquals(Minutes.minutes(-1), converter.convertFrom("-1"));
        assertEquals(Minutes.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Minutes.MAX_VALUE, converter.convertFrom("2147483647"));
    }

    @Test
    public void testConvertFromTooBig() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("2147483648")
        );
    }

    @Test
    public void testConvertFromTooSmall() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("-2147483649")
        );
    }

    @Test
    public void testConvertFromEmpty() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Not a number")
        );
    }

    @Test
    public void testConvertTo() {
        assertEquals("0", converter.convertTo(Minutes.ZERO));
        assertEquals("1", converter.convertTo(Minutes.ONE));
        assertEquals("-1", converter.convertTo(Minutes.minutes(-1)));
        assertEquals("-2147483648", converter.convertTo(Minutes.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Minutes.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
