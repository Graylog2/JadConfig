package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.hash.HashCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link HashCodeConverter}.
 */
public class HashCodeConverterTest {
    private HashCodeConverter converter;

    @Before
    public void setUp() {
        converter = new HashCodeConverter();
    }

    @Test
    public void testConvertFrom() {
        Assert.assertEquals(HashCode.fromString("86fb269d190d2c85f6e0468ceca42a20"), converter.convertFrom("86fb269d190d2c85f6e0468ceca42a20"));
        Assert.assertEquals(HashCode.fromString("d3486ae9136e7856bc42212385ea797094475802"), converter.convertFrom("d3486ae9136e7856bc42212385ea797094475802"));
        Assert.assertEquals(HashCode.fromString("c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a"), converter.convertFrom("c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a"));
        Assert.assertEquals(HashCode.fromString("f6cde2a0f819314cdde55fc227d8d7dae3d28cc556222a0a8ad66d91ccad4aad6094f517a2182360c9aacf6a3dc323162cb6fd8cdffedb0fe038f55e85ffb5b6"), converter.convertFrom("f6cde2a0f819314cdde55fc227d8d7dae3d28cc556222a0a8ad66d91ccad4aad6094f517a2182360c9aacf6a3dc323162cb6fd8cdffedb0fe038f55e85ffb5b6"));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {
        converter.convertFrom(null);
    }


    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {
        converter.convertFrom("Not a hash code#123");
    }

    @Test
    public void testConvertTo() {
        Assert.assertEquals("86fb269d190d2c85f6e0468ceca42a20", converter.convertTo(HashCode.fromString("86fb269d190d2c85f6e0468ceca42a20")));
        Assert.assertEquals("d3486ae9136e7856bc42212385ea797094475802", converter.convertTo(HashCode.fromString("d3486ae9136e7856bc42212385ea797094475802")));
        Assert.assertEquals("c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a", converter.convertTo(HashCode.fromString("c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a")));
        Assert.assertEquals("f6cde2a0f819314cdde55fc227d8d7dae3d28cc556222a0a8ad66d91ccad4aad6094f517a2182360c9aacf6a3dc323162cb6fd8cdffedb0fe038f55e85ffb5b6", converter.convertTo(HashCode.fromString("f6cde2a0f819314cdde55fc227d8d7dae3d28cc556222a0a8ad66d91ccad4aad6094f517a2182360c9aacf6a3dc323162cb6fd8cdffedb0fe038f55e85ffb5b6")));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {
        converter.convertTo(null);
    }
}
