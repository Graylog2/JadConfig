package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringSortedSetConverter}
 *
 * @author jschalanda
 */
public class StringSortedSetConverterTest {
    private StringSortedSetConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new StringSortedSetConverter();
    }

    @Test
    public void testConvertFrom() {
        Assertions.assertEquals(new TreeSet<String>(), converter.convertFrom(""));
        Assertions.assertEquals(0, converter.convertFrom(",").size());
        Assertions.assertEquals(0, converter.convertFrom(",,,,,").size());
        Assertions.assertEquals(1, converter.convertFrom("one").size());
        Assertions.assertEquals(1, converter.convertFrom("one;two;three").size());
        Assertions.assertEquals(3, converter.convertFrom("one,two,three").size());

        final SortedSet<String> result = converter.convertFrom("item1,item1,item3,item2");
        final SortedSet<String> expected = new TreeSet<>(Arrays.asList("item1", "item2", "item3"));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() {
        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.add("item3");
        sortedSet.add("item1");
        sortedSet.add("item2");

        Assertions.assertEquals("", converter.convertTo(new TreeSet<String>()));
        Assertions.assertEquals("item1,item2,item3", converter.convertTo(sortedSet));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
