package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.converters.UUIDConverter}
 */
public class ClassConverterTest {
    private ClassConverter converter;

    @Before
    public void setUp() {
        converter = new ClassConverter();
    }

    @Test
    public void testConvertFrom() {
        Class<?> clazz = String.class;
        Assert.assertEquals(clazz, converter.convertFrom(clazz.getCanonicalName()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {
        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Invalid Class");
    }

    @Test
    public void testConvertTo() {
        Class<?> clazz = String.class;
        Assert.assertEquals(clazz.getCanonicalName(), converter.convertTo(clazz));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
