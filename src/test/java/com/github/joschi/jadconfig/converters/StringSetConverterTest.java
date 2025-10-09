package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Joiner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringSetConverter}
 *
 * @author jschalanda
 */
public class StringSetConverterTest {
    private StringSetConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new StringSetConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(Collections.<String>emptySet(), converter.convertFrom(""));
        Assertions.assertEquals(0, converter.convertFrom(",").size());
        Assertions.assertEquals(0, converter.convertFrom(",,,,,").size());
        Assertions.assertEquals(1, converter.convertFrom("one").size());
        Assertions.assertEquals(1, converter.convertFrom("one;two;three").size());
        Assertions.assertEquals(3, converter.convertFrom("one,two,three").size());
        Assertions.assertEquals(3, converter.convertFrom("one,one,two,three").size());
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Assertions.assertEquals("", converter.convertTo(Collections.<String>emptySet()));
        Assertions.assertEquals(Joiner.on(',').join(set), converter.convertTo(set));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
