package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

/**
 * Unit tests for {@link CurrencyConverter}
 *
 * @author jschalanda
 */
public class CurrencyConverterTest {

    private CurrencyConverter converter;

    @Before
    public void setUp() {

        converter = new CurrencyConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Currency.getInstance("EUR"), converter.convertFrom("EUR"));
        Assert.assertEquals(Currency.getInstance("USD"), converter.convertFrom("USD"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {

        converter.convertFrom("");
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("EUR", converter.convertTo(Currency.getInstance("EUR")));
        Assert.assertEquals("USD", converter.convertTo(Currency.getInstance("USD")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
