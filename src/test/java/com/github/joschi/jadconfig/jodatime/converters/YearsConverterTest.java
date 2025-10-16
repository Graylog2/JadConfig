package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.joda.time.Years;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link YearsConverter}
 *
 * @author jschalanda
 */
public class YearsConverterTest {
    private YearsConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new YearsConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Years.ZERO, converter.convertFrom("0"));
        assertEquals(Years.ONE, converter.convertFrom("1"));
        assertEquals(Years.years(-1), converter.convertFrom("-1"));
        assertEquals(Years.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Years.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Years.ZERO));
        assertEquals("1", converter.convertTo(Years.ONE));
        assertEquals("-1", converter.convertTo(Years.years(-1)));
        assertEquals("-2147483648", converter.convertTo(Years.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Years.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
