package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.cache.CacheBuilderSpec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CacheBuilderSpecConverter}.
 */
public class CacheBuilderSpecConverterTest {
    private CacheBuilderSpecConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new CacheBuilderSpecConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertTrue(converter.convertFrom("maximumSize=100").toParsableString().contains("maximumSize=100"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {
        Assertions.assertEquals("maximumSize=100", converter.convertTo(CacheBuilderSpec.parse("maximumSize=100")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
