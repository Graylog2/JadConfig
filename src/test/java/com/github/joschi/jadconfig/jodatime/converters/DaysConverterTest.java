package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Days;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.DaysConverter}
 *
 * @author jschalanda
 */
public class DaysConverterTest {
    private DaysConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new DaysConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Days.ZERO, converter.convertFrom("0"));
        assertEquals(Days.ONE, converter.convertFrom("1"));
        assertEquals(Days.days(-1), converter.convertFrom("-1"));
        assertEquals(Days.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Days.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Days.ZERO));
        assertEquals("1", converter.convertTo(Days.ONE));
        assertEquals("-1", converter.convertTo(Days.days(-1)));
        assertEquals("-2147483648", converter.convertTo(Days.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Days.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
