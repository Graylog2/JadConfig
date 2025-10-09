package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link NoConverter}
 *
 * @author jschalanda
 */
public class NoConverterTest {

    private NoConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new NoConverter();
    }

    @Test
    public void testConvertFrom() {
        assertThrows(UnsupportedOperationException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void testConvertTo() {
        assertThrows(UnsupportedOperationException.class,
                () -> converter.convertTo("")
        );
    }
}
