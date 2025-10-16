package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link InetAddressConverter}
 *
 * @author jschalanda
 */
public class InetAddressConverterTest {

    private InetAddressConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new InetAddressConverter();
    }

    @Test
    public void testConvertFrom() throws UnknownHostException {

        Assertions.assertEquals(InetAddress.getByName("localhost"), converter.convertFrom("localhost"));
        Assertions.assertEquals(InetAddress.getByName("127.0.0.1"), converter.convertFrom("127.0.0.1"));
        Assertions.assertEquals(InetAddress.getByName("::1"), converter.convertFrom("::1"));
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
    public void testConvertTo() throws UnknownHostException {

        String loopbackCanonicalHostName = InetAddress.getLocalHost().getCanonicalHostName();

        Assertions.assertEquals(InetAddress.getByName("localhost").getCanonicalHostName(), converter.convertTo(InetAddress.getByName("localhost")));
        Assertions.assertEquals(loopbackCanonicalHostName, converter.convertTo(InetAddress.getLocalHost()));
        Assertions.assertEquals(InetAddress.getByName("").getCanonicalHostName(), converter.convertTo(InetAddress.getByName("")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
