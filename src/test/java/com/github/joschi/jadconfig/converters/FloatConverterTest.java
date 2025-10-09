package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link FloatConverter}
 *
 * @author jschalanda
 */
public class FloatConverterTest {

    private FloatConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new FloatConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Float.valueOf(0.0f), converter.convertFrom("0.0"));
        Assertions.assertEquals(Float.valueOf(-0.0f), converter.convertFrom("-0.0"));
        Assertions.assertEquals(Float.valueOf(1.0f), converter.convertFrom("1.0"));
        Assertions.assertEquals(Float.valueOf(-1.0f), converter.convertFrom("-1.0"));
        Assertions.assertEquals(Float.valueOf(1.0f), converter.convertFrom("+1.0"));

        Assertions.assertEquals(Float.MIN_VALUE, converter.convertFrom("1.4E-45"), 0.0f);
        Assertions.assertEquals(Float.MAX_VALUE, converter.convertFrom("3.4028235E38"), 0.0f);

        Assertions.assertEquals(0.0f, converter.convertFrom("1.4E-46"), 0.0f);
        Assertions.assertEquals(Float.MIN_NORMAL, converter.convertFrom("1.1754944E-38f"), 0.0f);

        Assertions.assertEquals(Float.POSITIVE_INFINITY, converter.convertFrom("3.4028235E39"), 0.0f);
        Assertions.assertEquals(Float.NEGATIVE_INFINITY, converter.convertFrom("-3.4028235E39"), 0.0f);
        Assertions.assertEquals(Float.POSITIVE_INFINITY, converter.convertFrom("Infinity"), 0.0f);
        Assertions.assertEquals(Float.NEGATIVE_INFINITY, converter.convertFrom("-Infinity"), 0.0f);
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

        Assertions.assertEquals("0.0", converter.convertTo(0.0f));
        Assertions.assertEquals("-0.0", converter.convertTo(-0.0f));
        Assertions.assertEquals("1.0", converter.convertTo(1.0f));
        Assertions.assertEquals("-1.0", converter.convertTo(-1.0f));

        Assertions.assertEquals("1.4E-45", converter.convertTo(Float.MIN_VALUE));
        Assertions.assertEquals("3.4028235E38", converter.convertTo(Float.MAX_VALUE));

        Assertions.assertEquals("1.1754944E-38", converter.convertTo(Float.MIN_NORMAL));

        Assertions.assertEquals("Infinity", converter.convertTo(Float.POSITIVE_INFINITY));
        Assertions.assertEquals("-Infinity", converter.convertTo(Float.NEGATIVE_INFINITY));
        Assertions.assertEquals("NaN", converter.convertTo(Float.NaN));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
