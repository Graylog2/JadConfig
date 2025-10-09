package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.SizeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link SizeUnitConverter}
 *
 * @author jschalanda
 */
public class SizeUnitConverterTest {
    private SizeUnitConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new SizeUnitConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(SizeUnit.BYTES, converter.convertFrom("bytes"));
        Assertions.assertEquals(SizeUnit.KILOBYTES, converter.convertFrom("KILObytes"));
        Assertions.assertEquals(SizeUnit.MEGABYTES, converter.convertFrom("MEGABYTES"));
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
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Invalid SizeUnit")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("BYTES", converter.convertTo(SizeUnit.BYTES));
        Assertions.assertEquals("KILOBYTES", converter.convertTo(SizeUnit.KILOBYTES));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
