package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CurrencyConverter}
 *
 * @author jschalanda
 */
public class CurrencyConverterTest {

    private CurrencyConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new CurrencyConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Currency.getInstance("EUR"), converter.convertFrom("EUR"));
        Assertions.assertEquals(Currency.getInstance("USD"), converter.convertFrom("USD"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromEmptyString() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("EUR", converter.convertTo(Currency.getInstance("EUR")));
        Assertions.assertEquals("USD", converter.convertTo(Currency.getInstance("USD")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
