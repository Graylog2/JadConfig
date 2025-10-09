package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link DoubleConverter}
 *
 * @author jschalanda
 */
public class DoubleConverterTest {

    private DoubleConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new DoubleConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Double.valueOf(0.0d), converter.convertFrom("0.0"));
        Assertions.assertEquals(Double.valueOf(-0.0d), converter.convertFrom("-0.0"));
        Assertions.assertEquals(Double.valueOf(1.0d), converter.convertFrom("1.0"));
        Assertions.assertEquals(Double.valueOf(-1.0d), converter.convertFrom("-1.0"));
        Assertions.assertEquals(Double.valueOf(1.0d), converter.convertFrom("+1.0"));

        Assertions.assertEquals(Double.MIN_VALUE, converter.convertFrom("4.9E-324"), 0.0d);
        Assertions.assertEquals(Double.MAX_VALUE, converter.convertFrom("1.7976931348623157E308"), 0.0d);

        Assertions.assertEquals(0.0d, converter.convertFrom("4.9E-325"), 0.0d);
        Assertions.assertEquals(Double.MIN_NORMAL, converter.convertFrom("2.2250738585072014E-308"), 0.0d);

        Assertions.assertEquals(Double.POSITIVE_INFINITY, converter.convertFrom("Infinity"), 0.0d);
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, converter.convertFrom("-Infinity"), 0.0d);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, converter.convertFrom("1.7976931348623157E309"), 0.0d);
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, converter.convertFrom("-1.7976931348623157E309"), 0.0d);
        Assertions.assertTrue(converter.convertFrom("NaN").isNaN());
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

        Assertions.assertEquals("0.0", converter.convertTo(0.0d));
        Assertions.assertEquals("-0.0", converter.convertTo(-0.0d));
        Assertions.assertEquals("1.0", converter.convertTo(1.0d));
        Assertions.assertEquals("-1.0", converter.convertTo(-1.0d));

        Assertions.assertEquals("4.9E-324", converter.convertTo(Double.MIN_VALUE));
        Assertions.assertEquals("1.7976931348623157E308", converter.convertTo(Double.MAX_VALUE));
        Assertions.assertEquals("2.2250738585072014E-308", converter.convertTo(Double.MIN_NORMAL));

        Assertions.assertEquals("Infinity", converter.convertTo(Double.POSITIVE_INFINITY));
        Assertions.assertEquals("-Infinity", converter.convertTo(Double.NEGATIVE_INFINITY));
        Assertions.assertEquals("NaN", converter.convertTo(Double.NaN));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
