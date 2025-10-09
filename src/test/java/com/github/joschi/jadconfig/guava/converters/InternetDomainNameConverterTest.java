package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.InternetDomainName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link InternetDomainNameConverter}.
 */
public class InternetDomainNameConverterTest {
    private InternetDomainNameConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new InternetDomainNameConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(InternetDomainName.from("com"), converter.convertFrom("com"));
        Assertions.assertEquals(InternetDomainName.from("foo.co.uk"), converter.convertFrom("foo.co.uk"));
        Assertions.assertEquals(InternetDomainName.from("joschi.github.io"), converter.convertFrom("joschi.github.io"));
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
                () -> converter.convertFrom("Not an Internet domain name#123")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("com", converter.convertTo(InternetDomainName.from("com")));
        Assertions.assertEquals("foo.co.uk", converter.convertTo(InternetDomainName.from("foo.co.uk")));
        Assertions.assertEquals("joschi.github.io", converter.convertTo(InternetDomainName.from("joschi.github.io")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
