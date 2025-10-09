package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Seconds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.jodatime.converters.SecondsConverter}
 *
 * @author jschalanda
 */
public class SecondsConverterTest {
    private SecondsConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new SecondsConverter();
    }

    @Test
    public void testConvertFrom() {
        assertEquals(Seconds.ZERO, converter.convertFrom("0"));
        assertEquals(Seconds.ONE, converter.convertFrom("1"));
        assertEquals(Seconds.seconds(-1), converter.convertFrom("-1"));
        assertEquals(Seconds.MIN_VALUE, converter.convertFrom("-2147483648"));
        assertEquals(Seconds.MAX_VALUE, converter.convertFrom("2147483647"));
    }

    @Test
    public void testConvertFromTooBig() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("2147483648")
        );
    }

    @Test
    public void testConvertFromTooSmall() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("-2147483649")
        );
    }

    @Test
    public void testConvertFromEmpty() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertInvalid() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("Not a number")
        );
    }

    @Test
    public void testConvertTo() {
        assertEquals("0", converter.convertTo(Seconds.ZERO));
        assertEquals("1", converter.convertTo(Seconds.ONE));
        assertEquals("-1", converter.convertTo(Seconds.seconds(-1)));
        assertEquals("-2147483648", converter.convertTo(Seconds.MIN_VALUE));
        assertEquals("2147483647", converter.convertTo(Seconds.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
