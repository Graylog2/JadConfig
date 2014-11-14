package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.cache.CacheBuilderSpec;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link CacheBuilderSpecConverter}.
 */
public class CacheBuilderSpecConverterTest {
    private CacheBuilderSpecConverter converter;

    @Before
    public void setUp() {
        converter = new CacheBuilderSpecConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertTrue(converter.convertFrom("maximumSize=100").toParsableString().contains("maximumSize=100"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("maximumSize=100", converter.convertTo(CacheBuilderSpec.parse("maximumSize=100")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
