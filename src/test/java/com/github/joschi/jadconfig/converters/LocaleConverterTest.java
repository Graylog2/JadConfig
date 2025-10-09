package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link LocaleConverter}
 *
 * @author jschalanda
 */
public class LocaleConverterTest {

    private LocaleConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new LocaleConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Locale.GERMAN, converter.convertFrom("de"));
        Assertions.assertEquals(Locale.GERMANY, converter.convertFrom("de_DE"));
        Assertions.assertEquals(Locale.US, converter.convertFrom("en_US"));
        Assertions.assertEquals(new Locale("es", "ES", "Traditional_WIN"), converter.convertFrom("es_ES_Traditional_WIN"));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals(Locale.GERMAN.toString(), converter.convertTo(Locale.GERMAN));
        Assertions.assertEquals(Locale.US.toString(), converter.convertTo(Locale.US));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
