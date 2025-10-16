package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link TimeUnitConverter}
 *
 * @author jschalanda
 */
public class TimeUnitConverterTest {
    private TimeUnitConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new TimeUnitConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(TimeUnit.DAYS, converter.convertFrom("days"));
        Assertions.assertEquals(TimeUnit.HOURS, converter.convertFrom("HoUrS"));
        Assertions.assertEquals(TimeUnit.MINUTES, converter.convertFrom("MINUTES"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromEmptyString()  {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class, () ->
                converter.convertFrom("Invalid TimeUnit")
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("DAYS", converter.convertTo(TimeUnit.DAYS));
        Assertions.assertEquals("NANOSECONDS", converter.convertTo(TimeUnit.NANOSECONDS));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class, () ->
                converter.convertTo(null)
        );
    }
}
