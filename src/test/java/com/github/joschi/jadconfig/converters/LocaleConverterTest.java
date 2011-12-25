package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

/**
 * Unit tests for {@link LocaleConverter}
 *
 * @author jschalanda
 */
public class LocaleConverterTest {

    private LocaleConverter converter;

    @Before
    public void setUp() {

        converter = new LocaleConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Locale.GERMANY, converter.convertFrom("de-DE"));
        Assert.assertEquals(Locale.US, converter.convertFrom("en-US"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals(Locale.GERMAN.toLanguageTag(), converter.convertTo(Locale.GERMAN));
        Assert.assertEquals(Locale.US.toLanguageTag(), converter.convertTo(Locale.US));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
