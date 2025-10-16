package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link Inet6AddressConverter}
 *
 * @author jschalanda
 */
public class Inet6AddressConverterTest {

    private static final String INET6ADDRESS_LOCALHOST = "::1";
    private static final String INET6ADDRESS_EXAMPLE_COM = "2001:500:88:200::10";
    private static final String INET4ADDRESS_EXAMPLE_COM = "192.0.43.10";

    private Inet6AddressConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new Inet6AddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assertions.assertEquals(InetAddress.getByName(INET6ADDRESS_LOCALHOST), converter.convertFrom(INET6ADDRESS_LOCALHOST));
        Assertions.assertEquals(InetAddress.getByName(INET6ADDRESS_EXAMPLE_COM), converter.convertFrom(INET6ADDRESS_EXAMPLE_COM));
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
                () -> converter.convertFrom("Not a hostname#123")
        );
    }

    @Test
    public void testConvertFromInet4Address() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(INET4ADDRESS_EXAMPLE_COM)
        );
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        Inet6Address loopbackInet6Address = (Inet6Address) InetAddress.getByName(INET6ADDRESS_LOCALHOST);
        Inet6Address exampleInet6Address = (Inet6Address) InetAddress.getByName(INET6ADDRESS_EXAMPLE_COM);

        Assertions.assertEquals(InetAddress.getByName(INET6ADDRESS_EXAMPLE_COM).getHostAddress(), converter.convertTo(exampleInet6Address));
        Assertions.assertEquals(loopbackInet6Address.getHostAddress(), converter.convertTo(loopbackInet6Address));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
