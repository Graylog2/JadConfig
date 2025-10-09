package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.net.HostAndPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link HostAndPortConverter}.
 */
public class HostAndPortConverterTest {
    private HostAndPortConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new HostAndPortConverter();
    }

    @Test
    public void testConvertFrom()  {
        Assertions.assertEquals(HostAndPort.fromHost("localhost"), converter.convertFrom("localhost"));
        Assertions.assertEquals(HostAndPort.fromParts("localhost",100), converter.convertFrom("localhost:100"));
        Assertions.assertEquals(HostAndPort.fromHost("127.0.0.1"), converter.convertFrom("127.0.0.1"));
        Assertions.assertEquals(HostAndPort.fromParts("127.0.0.1", 100), converter.convertFrom("127.0.0.1:100"));
        Assertions.assertEquals(HostAndPort.fromHost("::1"), converter.convertFrom("::1"));
        Assertions.assertEquals(HostAndPort.fromParts("[::1]", 100), converter.convertFrom("[::1]:100"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("localhost", converter.convertTo(HostAndPort.fromString("localhost")));
        Assertions.assertEquals("localhost:100", converter.convertTo(HostAndPort.fromParts("localhost", 100)));
        Assertions.assertEquals("127.0.0.1", converter.convertTo(HostAndPort.fromString("127.0.0.1")));
        Assertions.assertEquals("127.0.0.1:100", converter.convertTo(HostAndPort.fromParts("127.0.0.1", 100)));
        Assertions.assertEquals("[::1]", converter.convertTo(HostAndPort.fromString("::1")));
        Assertions.assertEquals("[::1]", converter.convertTo(HostAndPort.fromString("[::1]")));
        Assertions.assertEquals("[::1]:100", converter.convertTo(HostAndPort.fromParts("[::1]", 100)));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
