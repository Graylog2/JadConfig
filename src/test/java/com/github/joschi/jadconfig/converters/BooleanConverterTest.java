package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link BooleanConverter}
 *
 * @author jschalanda
 */
public class BooleanConverterTest {

    private BooleanConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new BooleanConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertTrue(converter.convertFrom("true"));
        Assertions.assertTrue(converter.convertFrom("tRuE"));
        Assertions.assertFalse(converter.convertFrom("false"));
        Assertions.assertFalse(converter.convertFrom("fAlSe"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertEmpty() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("")
        );
    }

    @Test
    public void testConvertInvalid() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("yes")
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("true", converter.convertTo(true));
        Assertions.assertEquals("false", converter.convertTo(false));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
