package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link DecimalFormatConverter}
 *
 * @author jschalanda
 */
public class DecimalFormatConverterTest {

    private DecimalFormatConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new DecimalFormatConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(new DecimalFormat("0.###E0"), converter.convertFrom("0.###E0"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("0.###E0", converter.convertTo(new DecimalFormat("0.###E0")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
