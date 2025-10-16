package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CharsetConverter}
 *
 * @author jschalanda
 */
public class CharsetConverterTest {

    private CharsetConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new CharsetConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Charset.forName("UTF-8"), converter.convertFrom("UTF-8"));
        Assertions.assertEquals(Charset.forName("ISO-8859-1"), converter.convertFrom("ISO-8859-1"));
        Assertions.assertEquals(Charset.defaultCharset(), converter.convertFrom(Charset.defaultCharset().name()));
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
    public void testConvertTo() {

        Assertions.assertEquals("UTF-8", converter.convertTo(Charset.forName("UTF-8")));
        Assertions.assertEquals("ISO-8859-1", converter.convertTo(Charset.forName("ISO-8859-1")));
        Assertions.assertEquals(Charset.defaultCharset().name(), converter.convertTo(Charset.defaultCharset()));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
