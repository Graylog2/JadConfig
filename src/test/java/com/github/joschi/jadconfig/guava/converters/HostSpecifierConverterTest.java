package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.HostSpecifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link HostSpecifierConverter}.
 */
public class HostSpecifierConverterTest {
    private HostSpecifierConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new HostSpecifierConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(HostSpecifier.fromValid("joschi.github.io"), converter.convertFrom("joschi.github.io"));
        Assertions.assertEquals(HostSpecifier.fromValid("127.0.0.1"), converter.convertFrom("127.0.0.1"));
        Assertions.assertEquals(HostSpecifier.fromValid("::1"), converter.convertFrom("::1"));
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
                () -> converter.convertFrom("Not a host specifier#123")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("joschi.github.io", converter.convertTo(HostSpecifier.fromValid("joschi.github.io")));
        Assertions.assertEquals("127.0.0.1", converter.convertTo(HostSpecifier.fromValid("127.0.0.1")));
        Assertions.assertEquals("[::1]", converter.convertTo(HostSpecifier.fromValid("::1")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
