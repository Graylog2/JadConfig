package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link URIConverter}
 *
 * @author jschalanda
 */
public class URIConverterTest {

    private URIConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new URIConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(URI.create("http://localhost:80/path?key=value#fragment"), converter.convertFrom("http://localhost:80/path?key=value#fragment"));
        Assertions.assertEquals(URI.create(""), converter.convertFrom(""));
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
                () -> converter.convertFrom("Not an URI")
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("http://localhost:80/path?key=value#fragment", converter.convertTo(URI.create("http://localhost:80/path?key=value#fragment")));
        Assertions.assertEquals("", converter.convertTo(URI.create("")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
