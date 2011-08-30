package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link StringConverter}
 *
 * @author jschalanda
 */
public class StringConverterTest {

    StringConverter converter;

    @Before
    public void setUp() {

        converter = new StringConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals("", converter.convert(""));
        Assert.assertEquals("Test", converter.convert("Test"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        converter.convert(null);
    }
}
