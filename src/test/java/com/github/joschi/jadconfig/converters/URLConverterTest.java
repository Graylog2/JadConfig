package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link URLConverter}
 *
 * @author jschalanda
 */
public class URLConverterTest {

    private URLConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new URLConverter();
    }

    @Test
    public void testConvertFrom() throws MalformedURLException {

        Assertions.assertEquals(new URL("http://localhost:80/path?key=value#fragment"), converter.convertFrom("http://localhost:80/path?key=value#fragment"));
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
                () -> converter.convertFrom("Not an URL")
        );
    }

    @Test
    public void testConvertTo() throws MalformedURLException {
        Assertions.assertEquals("http://localhost:80/path?key=value#fragment", converter.convertTo(new URL("http://localhost:80/path?key=value#fragment")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
