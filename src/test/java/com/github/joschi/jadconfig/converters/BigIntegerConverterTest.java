package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.BigIntegerConverter}
 *
 * @author jschalanda
 */
public class BigIntegerConverterTest {

    private BigIntegerConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new BigIntegerConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(BigInteger.ZERO, converter.convertFrom("0"));
        Assertions.assertEquals(BigInteger.ONE, converter.convertFrom("1"));
        Assertions.assertEquals(BigInteger.ONE.negate(), converter.convertFrom("-1"));
        Assertions.assertEquals(new BigInteger("1234567890"), converter.convertFrom("1234567890"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("Not a number")
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("0", converter.convertTo(BigInteger.ZERO));
        Assertions.assertEquals("1", converter.convertTo(BigInteger.ONE));
        Assertions.assertEquals("-1", converter.convertTo(BigInteger.ONE.negate()));
        Assertions.assertEquals("1234567890", converter.convertTo(new BigInteger("1234567890")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
