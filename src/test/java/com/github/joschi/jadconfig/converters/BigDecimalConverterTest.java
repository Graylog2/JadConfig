package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link BigDecimalConverter}
 *
 * @author jschalanda
 */
public class BigDecimalConverterTest {

    private BigDecimalConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new BigDecimalConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(BigDecimal.ZERO, converter.convertFrom("0"));
        Assertions.assertEquals(new BigDecimal("0.0"), converter.convertFrom("0.0"));
        Assertions.assertEquals(new BigDecimal("-0.0"), converter.convertFrom("-0.0"));
        Assertions.assertEquals(BigDecimal.ONE, converter.convertFrom("1"));
        Assertions.assertEquals(new BigDecimal("1.0"), converter.convertFrom("1.0"));
        Assertions.assertEquals(new BigDecimal("-1.0"), converter.convertFrom("-1.0"));
        Assertions.assertEquals(new BigDecimal("1.0"), converter.convertFrom("+1.0"));
        Assertions.assertEquals(new BigDecimal("1234567890"), converter.convertFrom("1234567890"));
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
                () -> converter.convertFrom("Not a number")
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals(BigDecimal.ZERO.toString(), converter.convertTo(BigDecimal.ZERO));
        Assertions.assertEquals(BigDecimal.ONE.toString(), converter.convertTo(BigDecimal.ONE));
        Assertions.assertEquals(BigDecimal.ONE.negate().toString(), converter.convertTo(BigDecimal.ONE.negate()));
        Assertions.assertEquals(new BigDecimal("1234567890").toString(), converter.convertTo(new BigDecimal("1234567890")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
