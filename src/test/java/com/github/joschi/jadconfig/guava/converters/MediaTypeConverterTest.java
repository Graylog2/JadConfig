package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.MediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link MediaTypeConverter}.
 */
public class MediaTypeConverterTest {
    private MediaTypeConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new MediaTypeConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(MediaType.ANY_TYPE, converter.convertFrom("*/*"));
        Assertions.assertEquals(MediaType.create("text", "html"), converter.convertFrom("text/html"));
        Assertions.assertEquals(MediaType.JSON_UTF_8, converter.convertFrom("application/json; charset=utf-8"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }


    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Not a media type#123")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("*/*", converter.convertTo(MediaType.ANY_TYPE));
        Assertions.assertEquals("text/html", converter.convertTo(MediaType.create("text", "html")));
        Assertions.assertEquals("application/json; charset=utf-8", converter.convertTo(MediaType.JSON_UTF_8));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
