package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.primitives.UnsignedInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link UnsignedIntegerConverter}
 */
public class UnsignedIntegerConverterTest {
    private UnsignedIntegerConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new UnsignedIntegerConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(UnsignedInteger.ZERO, converter.convertFrom("0"));
        Assertions.assertEquals(UnsignedInteger.ONE, converter.convertFrom("1"));
        Assertions.assertEquals(UnsignedInteger.valueOf(Integer.MAX_VALUE), converter.convertFrom("2147483647"));
        Assertions.assertEquals(UnsignedInteger.MAX_VALUE, converter.convertFrom("4294967295"));
    }

    @Test
    public void testConvertFromTooBig() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("4294967296")
        );
    }

    @Test
    public void testConvertFromNegative() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("-1")
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
        Assertions.assertEquals("0", converter.convertTo(UnsignedInteger.ZERO));
        Assertions.assertEquals("1", converter.convertTo(UnsignedInteger.ONE));
        Assertions.assertEquals("2147483647", converter.convertTo(UnsignedInteger.valueOf(Integer.MAX_VALUE)));
        Assertions.assertEquals("4294967295", converter.convertTo(UnsignedInteger.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
