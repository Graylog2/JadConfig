package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link Inet4AddressConverter}
 *
 * @author jschalanda
 */
public class Inet4AddressConverterTest {

    private static final String INET4ADDRESS_LOCALHOST = "127.0.0.1";
    private static final String INET4ADDRESS_EXAMPLE_COM = "192.0.43.10";
    private static final String INET6ADDRESS_EXAMPLE_COM = "2001:500:88:200::10";

    private Inet4AddressConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new Inet4AddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assertions.assertEquals(InetAddress.getByName(INET4ADDRESS_LOCALHOST), converter.convertFrom(INET4ADDRESS_LOCALHOST));
        Assertions.assertEquals(InetAddress.getByName(INET4ADDRESS_EXAMPLE_COM), converter.convertFrom(INET4ADDRESS_EXAMPLE_COM));
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
    public void testConvertFromInet6Address() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(INET6ADDRESS_EXAMPLE_COM)
        );
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        Inet4Address loopbackInet4Address = (Inet4Address) InetAddress.getByName(INET4ADDRESS_LOCALHOST);
        Inet4Address exampleInet4Address = (Inet4Address) InetAddress.getByName(INET4ADDRESS_EXAMPLE_COM);

        Assertions.assertEquals(InetAddress.getByName(INET4ADDRESS_EXAMPLE_COM).getHostAddress(), converter.convertTo(exampleInet4Address));
        Assertions.assertEquals(loopbackInet4Address.getHostAddress(), converter.convertTo(loopbackInet4Address));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
