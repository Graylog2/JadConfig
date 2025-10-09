package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.SizeConverter}
 *
 * @author jschalanda
 */
public class SizeConverterTest {
    private SizeConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new SizeConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(Size.petabytes(1l), converter.convertFrom("1 PB"));
        Assertions.assertEquals(Size.terabytes(2l), converter.convertFrom("2   T"));
        Assertions.assertEquals(Size.gigabytes(3l), converter.convertFrom("3g"));
        Assertions.assertEquals(Size.megabytes(4l), converter.convertFrom("4 megabytes"));
        Assertions.assertEquals(Size.kilobytes(5l), converter.convertFrom("5 KiB"));
        Assertions.assertEquals(Size.bytes(6l), converter.convertFrom("6 B"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromEmptyString() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Invalid Size")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("1 byte", converter.convertTo(Size.bytes(1)));
        Assertions.assertEquals("2 bytes", converter.convertTo(Size.bytes(2)));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
