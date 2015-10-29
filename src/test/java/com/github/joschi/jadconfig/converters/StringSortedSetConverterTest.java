package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Unit tests for {@link StringSortedSetConverter}
 *
 * @author jschalanda
 */
public class StringSortedSetConverterTest {
    private StringSortedSetConverter converter;

    @Before
    public void setUp() {
        converter = new StringSortedSetConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(new TreeSet<String>(), converter.convertFrom(""));
        Assert.assertEquals(0, converter.convertFrom(",").size());
        Assert.assertEquals(0, converter.convertFrom(",,,,,").size());
        Assert.assertEquals(1, converter.convertFrom("one").size());
        Assert.assertEquals(1, converter.convertFrom("one;two;three").size());
        Assert.assertEquals(3, converter.convertFrom("one,two,three").size());

        final SortedSet<String> result = converter.convertFrom("item1,item1,item3,item2");
        final SortedSet<String> expected = new TreeSet<>(Arrays.asList("item1", "item2", "item3"));
        Assert.assertEquals(expected, result);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {
        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.add("item3");
        sortedSet.add("item1");
        sortedSet.add("item2");

        Assert.assertEquals("", converter.convertTo(new TreeSet<String>()));
        Assert.assertEquals("item1,item2,item3", converter.convertTo(sortedSet));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
