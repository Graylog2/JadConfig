package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Unit tests for {@link StringListConverter}
 *
 * @author jschalanda
 */
public class StringListConverterTest {

    StringListConverter converter;

    @Before
    public void setUp() {

        converter = new StringListConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(Collections.<String>emptyList(), converter.convert(""));
        Assert.assertEquals(0, converter.convert(",").size());
        Assert.assertEquals(0, converter.convert(",,,,,").size());
        Assert.assertEquals(1, converter.convert("one").size());
        Assert.assertEquals(1, converter.convert("one;two;three").size());
        Assert.assertEquals(3, converter.convert("one,two,three").size());
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        converter.convert(null);
    }
}
