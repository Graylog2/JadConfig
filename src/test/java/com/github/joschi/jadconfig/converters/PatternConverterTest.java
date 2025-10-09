package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link PatternConverter}
 *
 * @author jschalanda
 */
public class PatternConverterTest {

    private PatternConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new PatternConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals("", converter.convertFrom("").pattern());
        Assertions.assertEquals(" ", converter.convertFrom(" ").pattern());
        Assertions.assertEquals("\\r\\n\r\n", converter.convertFrom("\\r\\n\r\n").pattern());
        Assertions.assertEquals("Test.*Patterns?", converter.convertFrom("Test.*Patterns?").pattern());
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {

        Assertions.assertEquals("", converter.convertTo(Pattern.compile("")));
        Assertions.assertEquals(" ", converter.convertTo(Pattern.compile(" ")));
        Assertions.assertEquals("\\r\\n\r\n", converter.convertTo(Pattern.compile("\\r\\n\r\n")));
        Assertions.assertEquals("Test.*Patterns?", converter.convertTo(Pattern.compile("Test.*Patterns?")));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
