package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringConverter}
 *
 * @author jschalanda
 */
public class StringConverterTest {

    private StringConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new StringConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals("", converter.convertFrom(""));
        Assertions.assertEquals("Test", converter.convertFrom("Test"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("", converter.convertTo(""));
        Assertions.assertEquals("Test", converter.convertTo("Test"));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
