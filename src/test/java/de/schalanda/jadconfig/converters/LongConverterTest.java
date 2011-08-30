package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link LongConverter}
 *
 * @author jschalanda
 */
public class LongConverterTest {

    LongConverter converter;

    @Before
    public void setUp() {

        converter = new LongConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(Long.valueOf(0L), converter.convert("0"));
        Assert.assertEquals(Long.valueOf(1L), converter.convert("1"));
        Assert.assertEquals(Long.valueOf(-1L), converter.convert("-1"));
        Assert.assertEquals(Long.valueOf(1L), converter.convert("+1"));
        Assert.assertEquals(Long.MIN_VALUE, converter.convert("-9223372036854775808").longValue());
        Assert.assertEquals(Long.MAX_VALUE, converter.convert("9223372036854775807").longValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooBig() {

        converter.convert("9223372036854775808");
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooSmall() {

        converter.convert("-9223372036854775809");
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        converter.convert(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertInvalid() {

        converter.convert("Not a number");
    }
}
