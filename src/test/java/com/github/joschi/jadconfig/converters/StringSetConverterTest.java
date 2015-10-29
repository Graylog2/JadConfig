package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for {@link StringSetConverter}
 *
 * @author jschalanda
 */
public class StringSetConverterTest {
    private StringSetConverter converter;

    @Before
    public void setUp() {
        converter = new StringSetConverter();
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
