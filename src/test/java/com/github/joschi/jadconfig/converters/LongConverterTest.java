package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link LongConverter}
 *
 * @author jschalanda
 */
public class LongConverterTest {

    private LongConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new LongConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Long.valueOf(0L), converter.convertFrom("0"));
        Assertions.assertEquals(Long.valueOf(1L), converter.convertFrom("1"));
        Assertions.assertEquals(Long.valueOf(-1L), converter.convertFrom("-1"));
        Assertions.assertEquals(Long.MIN_VALUE, converter.convertFrom("-9223372036854775808").longValue());
        Assertions.assertEquals(Long.MAX_VALUE, converter.convertFrom("9223372036854775807").longValue());
    }

    @Test
    public void testConvertFromTooBig() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("9223372036854775808")
        );
    }

    @Test
    public void testConvertFromTooSmall() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("-9223372036854775809")
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

        Assertions.assertEquals("0", converter.convertTo(0L));
        Assertions.assertEquals("1", converter.convertTo(1L));
        Assertions.assertEquals("-1", converter.convertTo(-1L));
        Assertions.assertEquals("-9223372036854775808", converter.convertTo(Long.MIN_VALUE));
        Assertions.assertEquals("9223372036854775807", converter.convertTo(Long.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
