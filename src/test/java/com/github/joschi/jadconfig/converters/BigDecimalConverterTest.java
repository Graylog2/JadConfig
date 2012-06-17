package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Unit tests for {@link BigDecimalConverter}
 *
 * @author jschalanda
 */
public class BigDecimalConverterTest {

    private BigDecimalConverter converter;

    @Before
    public void setUp() {

        converter = new BigDecimalConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(BigDecimal.ZERO, converter.convertFrom("0"));
        Assert.assertEquals(new BigDecimal("0.0"), converter.convertFrom("0.0"));
        Assert.assertEquals(new BigDecimal("-0.0"), converter.convertFrom("-0.0"));
        Assert.assertEquals(BigDecimal.ONE, converter.convertFrom("1"));
        Assert.assertEquals(new BigDecimal("1.0"), converter.convertFrom("1.0"));
        Assert.assertEquals(new BigDecimal("-1.0"), converter.convertFrom("-1.0"));
        Assert.assertEquals(new BigDecimal("1.0"), converter.convertFrom("+1.0"));
        Assert.assertEquals(new BigDecimal("1234567890"), converter.convertFrom("1234567890"));
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

        Assert.assertEquals(BigDecimal.ZERO.toString(), converter.convertTo(BigDecimal.ZERO));
        Assert.assertEquals(BigDecimal.ONE.toString(), converter.convertTo(BigDecimal.ONE));
        Assert.assertEquals(BigDecimal.ONE.negate().toString(), converter.convertTo(BigDecimal.ONE.negate()));
        Assert.assertEquals(new BigDecimal("1234567890").toString(), converter.convertTo(new BigDecimal("1234567890")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
