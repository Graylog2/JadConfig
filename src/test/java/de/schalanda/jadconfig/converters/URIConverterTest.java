package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * Unit tests for {@link URIConverter}
 *
 * @author jschalanda
 */
public class URIConverterTest {

    URIConverter converter;

    @Before
    public void setUp() {

        converter = new URIConverter();
    }

    @Test
    public void testConvert() {

        Assert.assertEquals(URI.create("http://localhost:80/path?key=value#fragment"), converter.convert("http://localhost:80/path?key=value#fragment"));
        Assert.assertEquals(URI.create(""), converter.convert(""));
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        converter.convert(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertInvalid() {

        converter.convert("Not an URI");
    }
}
