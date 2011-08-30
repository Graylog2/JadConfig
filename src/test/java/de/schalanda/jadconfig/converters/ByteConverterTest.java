package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ByteConverter}
 *
 * @author jschalanda
 */
public class ByteConverterTest {

    ByteConverter converter;

    @Before
    public void setUp() {

        converter = new ByteConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(Byte.valueOf((byte) 0), converter.convert("0"));
        Assert.assertEquals(Byte.valueOf((byte) 1), converter.convert("1"));
        Assert.assertEquals(Byte.valueOf((byte) -1), converter.convert("-1"));
        Assert.assertEquals(Byte.valueOf((byte) 1), converter.convert("+1"));
        Assert.assertEquals(Byte.MIN_VALUE, converter.convert("-128").byteValue());
        Assert.assertEquals(Byte.MAX_VALUE, converter.convert("127").byteValue());
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooBig() {

        converter.convert("128");
    }

    @Test(expected = ParameterException.class)
    public void testConvertTooSmall() {

        converter.convert("-129");
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
