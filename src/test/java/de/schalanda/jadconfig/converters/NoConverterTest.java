package de.schalanda.jadconfig.converters;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link NoConverter}
 *
 * @author jschalanda
 */
public class NoConverterTest {

    NoConverter converter;

    @Before
    public void setUp() {

        converter = new NoConverter();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConvert() {

        converter.convert("");
    }
}
