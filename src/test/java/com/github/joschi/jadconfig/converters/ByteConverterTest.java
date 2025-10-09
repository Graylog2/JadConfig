package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link ByteConverter}
 *
 * @author jschalanda
 */
public class ByteConverterTest {

    private ByteConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new ByteConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Byte.valueOf((byte) 0), converter.convertFrom("0"));
        Assertions.assertEquals(Byte.valueOf((byte) 1), converter.convertFrom("1"));
        Assertions.assertEquals(Byte.valueOf((byte) -1), converter.convertFrom("-1"));
        Assertions.assertEquals(Byte.MIN_VALUE, converter.convertFrom("-128").byteValue());
        Assertions.assertEquals(Byte.MAX_VALUE, converter.convertFrom("127").byteValue());
    }

    @Test
    public void testConvertFromTooBig() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("128")
        );
    }

    @Test
    public void testConvertFromTooSmall() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("-129")
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

        Assertions.assertEquals("0", converter.convertTo((byte) 0));
        Assertions.assertEquals("1", converter.convertTo((byte) 1));
        Assertions.assertEquals("-1", converter.convertTo((byte) -1));
        Assertions.assertEquals("-128", converter.convertTo(Byte.MIN_VALUE));
        Assertions.assertEquals("127", converter.convertTo(Byte.MAX_VALUE));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
