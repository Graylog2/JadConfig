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

        Assert.assertEquals(Locale.GERMAN, converter.convertFrom("de"));
        Assert.assertEquals(Locale.GERMANY, converter.convertFrom("de_DE"));
        Assert.assertEquals(Locale.US, converter.convertFrom("en_US"));
        Assert.assertEquals(new Locale("es", "ES", "Traditional_WIN"), converter.convertFrom("es_ES_Traditional_WIN"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        Assert.assertEquals(Locale.GERMAN.toString(), converter.convertTo(Locale.GERMAN));
        Assert.assertEquals(Locale.US.toString(), converter.convertTo(Locale.US));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
