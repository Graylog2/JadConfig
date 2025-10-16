package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link IntegerConverter}
 *
 * @author jschalanda
 */
public class IntegerConverterTest {

    private IntegerConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new IntegerConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Integer.valueOf(0), converter.convertFrom("0"));
        Assertions.assertEquals(Integer.valueOf(1), converter.convertFrom("1"));
        Assertions.assertEquals(Integer.valueOf(-1), converter.convertFrom("-1"));
        Assertions.assertEquals(Integer.MIN_VALUE, converter.convertFrom("-2147483648").intValue());
        Assertions.assertEquals(Integer.MAX_VALUE, converter.convertFrom("2147483647").intValue());
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
        Assertions.assertEquals("0", converter.convertTo(0));
        Assertions.assertEquals("1", converter.convertTo(1));
        Assertions.assertEquals("-1", converter.convertTo(-1));
        Assertions.assertEquals("-2147483648", converter.convertTo(Integer.MIN_VALUE));
        Assertions.assertEquals("2147483647", converter.convertTo(Integer.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
