package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.BigIntegerConverter}
 *
 * @author jschalanda
 */
public class BigIntegerConverterTest {

    private BigIntegerConverter converter;

    @Before
    public void setUp() {

        converter = new BigIntegerConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(BigInteger.ZERO, converter.convertFrom("0"));
        Assert.assertEquals(BigInteger.ONE, converter.convertFrom("1"));
        Assert.assertEquals(BigInteger.ONE.negate(), converter.convertFrom("-1"));
        Assert.assertEquals(new BigInteger("1234567890"), converter.convertFrom("1234567890"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Not a number");
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("0", converter.convertTo(BigInteger.ZERO));
        Assert.assertEquals("1", converter.convertTo(BigInteger.ONE));
        Assert.assertEquals("-1", converter.convertTo(BigInteger.ONE.negate()));
        Assert.assertEquals("1234567890", converter.convertTo(new BigInteger("1234567890")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
