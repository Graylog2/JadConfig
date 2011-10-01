package com.github.joschi.jadconfig.converters;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link NoConverter}
 *
 * @author jschalanda
 */
public class NoConverterTest {

    private NoConverter converter;

    @Before
    public void setUp() {

        converter = new NoConverter();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConvertFrom() {

        converter.convertFrom("");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConvertTo() {

        converter.convertTo("");
    }
}
