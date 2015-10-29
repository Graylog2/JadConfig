package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for {@link TrimmedStringSetConverter}
 *
 * @author jschalanda
 */
public class TrimmedStringSetConverterTest {
    private TrimmedStringSetConverter converter;

    @Before
    public void setUp() {
        converter = new TrimmedStringSetConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(Collections.<String>emptySet(), converter.convertFrom(""));
        Assert.assertEquals(0, converter.convertFrom(",").size());
        Assert.assertEquals(0, converter.convertFrom(",,,,,").size());
        Assert.assertEquals(1, converter.convertFrom("one").size());
        Assert.assertEquals(1, converter.convertFrom("one;two;three").size());
        Assert.assertEquals(3, converter.convertFrom("one,two,three").size());
        Assert.assertEquals(3, converter.convertFrom("one,one,two,three").size());

        final Set<String> result = converter.convertFrom("item1, item1\t,   item2,\nitem3");
        final Set<String> expected = new HashSet<>(Arrays.asList("item1", "item2", "item3"));
        Assert.assertEquals(expected, result);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Assert.assertEquals("", converter.convertTo(Collections.<String>emptySet()));
        Assert.assertEquals(Joiner.on(',').join(set), converter.convertTo(set));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
