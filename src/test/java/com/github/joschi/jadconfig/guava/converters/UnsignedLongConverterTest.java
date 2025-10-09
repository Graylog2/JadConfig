package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.primitives.UnsignedLong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link UnsignedLongConverter}
 */
public class UnsignedLongConverterTest {
    private UnsignedLongConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new UnsignedLongConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(UnsignedLong.ZERO, converter.convertFrom("0"));
        Assertions.assertEquals(UnsignedLong.ONE, converter.convertFrom("1"));
        Assertions.assertEquals(UnsignedLong.valueOf(Long.MAX_VALUE), converter.convertFrom("9223372036854775807"));
        Assertions.assertEquals(UnsignedLong.MAX_VALUE, converter.convertFrom("18446744073709551615"));
    }

    @Test
    public void testConvertFromTooBig() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("18446744073709551616")
        );
    }

    @Test
    public void testConvertFromNegative() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("-1")
        );
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("Not a number")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("0", converter.convertTo(UnsignedLong.ZERO));
        Assertions.assertEquals("1", converter.convertTo(UnsignedLong.ONE));
        Assertions.assertEquals("18446744073709551615", converter.convertTo(UnsignedLong.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
