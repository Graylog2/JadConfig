package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Unit tests for {@link StringListConverter}
 *
 * @author jschalanda
 */
public class StringListConverterTest {

    private StringListConverter converter;

    @Before
    public void setUp() {

        converter = new StringListConverter();
    }

    @Test
    public void testConvertFrom() {

        Assert.assertEquals(Collections.<String>emptyList(), converter.convertFrom(""));
        Assert.assertEquals(0, converter.convertFrom(",").size());
        Assert.assertEquals(0, converter.convertFrom(",,,,,").size());
        Assert.assertEquals(1, converter.convertFrom("one").size());
        Assert.assertEquals(1, converter.convertFrom("one;two;three").size());
        Assert.assertEquals(3, converter.convertFrom("one,two,three").size());
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() {

        List<String> list = new ArrayList<String>();
        list.add("one"); list.add("two"); list.add("three");

        Assert.assertEquals("", converter.convertTo(Collections.<String>emptyList()));
        Assert.assertEquals("one,two,three", converter.convertTo(list));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
