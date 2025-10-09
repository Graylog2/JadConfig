package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link ShortConverter}
 *
 * @author jschalanda
 */
public class ShortConverterTest {

    private ShortConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new ShortConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Short.valueOf((short) 0), converter.convertFrom("0"));
        Assertions.assertEquals(Short.valueOf((short) 1), converter.convertFrom("1"));
        Assertions.assertEquals(Short.valueOf((short) -1), converter.convertFrom("-1"));
        Assertions.assertEquals(Short.MIN_VALUE, converter.convertFrom("-32768").shortValue());
        Assertions.assertEquals(Short.MAX_VALUE, converter.convertFrom("32767").shortValue());
    }

    @Test
    public void testConvertFromTooBig(){
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("32768")
        );
    }

    @Test
    public void testConvertFromTooSmall() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("-32769")
        );
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromInvalid(){
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("Not a number")
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("0", converter.convertTo((short) 0));
        Assertions.assertEquals("1", converter.convertTo((short) 1));
        Assertions.assertEquals("-1", converter.convertTo((short) -1));
        Assertions.assertEquals("-32768", converter.convertTo(Short.MIN_VALUE));
        Assertions.assertEquals("32767", converter.convertTo(Short.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
            converter.convertTo(null)
        );
    }
}
