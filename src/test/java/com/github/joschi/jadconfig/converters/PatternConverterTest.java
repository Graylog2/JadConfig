package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Unit tests for {@link PatternConverter}
 *
 * @author jschalanda
 */
public class PatternConverterTest {

    private PatternConverter converter;

    @Before
    public void setUp() {

        converter = new PatternConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals("", converter.convertFrom("").pattern());
        Assert.assertEquals(" ", converter.convertFrom(" ").pattern());
        Assert.assertEquals("\\r\\n\r\n", converter.convertFrom("\\r\\n\r\n").pattern());
        Assert.assertEquals("Test.*Patterns?", converter.convertFrom("Test.*Patterns?").pattern());
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals("", converter.convertTo(Pattern.compile("")));
        Assert.assertEquals(" ", converter.convertTo(Pattern.compile(" ")));
        Assert.assertEquals("\\r\\n\r\n", converter.convertTo(Pattern.compile("\\r\\n\r\n")));
        Assert.assertEquals("Test.*Patterns?", converter.convertTo(Pattern.compile("Test.*Patterns?")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
