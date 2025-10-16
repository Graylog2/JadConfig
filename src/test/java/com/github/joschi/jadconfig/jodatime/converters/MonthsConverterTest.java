package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Months;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.MonthsConverter}
 *
 * @author jschalanda
 */
public class MonthsConverterTest {
    private MonthsConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new MonthsConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Months.ZERO, converter.convertFrom("0"));
        assertEquals(Months.ONE, converter.convertFrom("1"));
        assertEquals(Months.months(-1), converter.convertFrom("-1"));
        assertEquals(Months.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Months.MAX_VALUE, converter.convertFrom("2147483647"));
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
        assertEquals("0", converter.convertTo(Months.ZERO));
        assertEquals("1", converter.convertTo(Months.ONE));
        assertEquals("-1", converter.convertTo(Months.months(-1)));
        assertEquals("-2147483648", converter.convertTo(Months.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Months.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
