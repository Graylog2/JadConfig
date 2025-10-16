package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link InetSocketAddressConverter}
 *
 * @author jschalanda
 */
public class InetSocketAddressConverterTest {

    private InetSocketAddressConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new InetSocketAddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assertions.assertEquals(new InetSocketAddress("localhost", 1234), converter.convertFrom("localhost:1234"));
        Assertions.assertEquals(new InetSocketAddress("127.0.0.1", 1234), converter.convertFrom("127.0.0.1:1234"));
        Assertions.assertEquals(new InetSocketAddress("::1", 1234), converter.convertFrom("[::1]:1234"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromInvalidHostname() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Not a hostname#123:1234")
        );
    }

    @Test
    public void testConvertFromPortInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("localhost:99999")
        );
    }

    @Test
    public void testConvertFromPortMissing() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("localhost")
        );
    }

    @Test
    public void testConvertFromHostnameMissing() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(":123")
        );
    }

    @Test
    public void testConvertTo() throws UnknownHostException {

        Assertions.assertEquals("localhost:1234", converter.convertTo(InetSocketAddress.createUnresolved("localhost", 1234)));
        Assertions.assertEquals("127.0.0.1:1234", converter.convertTo(InetSocketAddress.createUnresolved("127.0.0.1", 1234)));
        Assertions.assertEquals("[::1]:1234", converter.convertTo(InetSocketAddress.createUnresolved("[::1]", 1234)));
        Assertions.assertEquals(String.format("[%s]:%d", InetAddress.getByName("::1").getHostAddress(), 1234),
                converter.convertTo(new InetSocketAddress("[::1]", 1234)));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
