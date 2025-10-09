package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringListConverter}
 *
 * @author jschalanda
 */
public class StringListConverterTest {

    private StringListConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new StringListConverter();
    }

    @Test
    public void testConvertFrom() {

        Assertions.assertEquals(Collections.<String>emptyList(), converter.convertFrom(""));
        Assertions.assertEquals(0, converter.convertFrom(",").size());
        Assertions.assertEquals(0, converter.convertFrom(",,,,,").size());
        Assertions.assertEquals(1, converter.convertFrom("one").size());
        Assertions.assertEquals(1, converter.convertFrom("one;two;three").size());
        Assertions.assertEquals(3, converter.convertFrom("one,two,three").size());
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {

        List<String> list = new ArrayList<String>();
        list.add("one"); list.add("two"); list.add("three");

        Assertions.assertEquals("", converter.convertTo(Collections.<String>emptyList()));
        Assertions.assertEquals("one,two,three", converter.convertTo(list));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
