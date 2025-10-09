package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.UUIDConverter}
 */
public class ClassConverterTest {
    private ClassConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new ClassConverter();
    }

    @Test
    public void testConvertFrom() {
        Class<?> clazz = String.class;
        Assertions.assertEquals(clazz, converter.convertFrom(clazz.getCanonicalName()));
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
                () -> converter.convertFrom("Invalid Class")
        );
    }

    @Test
    public void testConvertTo() {
        Class<?> clazz = String.class;
        Assertions.assertEquals(clazz.getCanonicalName(), converter.convertTo(clazz));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
