package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * Unit tests for {@link UUIDConverter}
 *
 * @author jschalanda
 */
public class UUIDConverterTest {

    private UUIDConverter converter;

    @Before
    public void setUp() {

        converter = new UUIDConverter();
    }

    @Test
    public void testConvertFrom() {

        UUID uuid = UUID.randomUUID();

        Assert.assertEquals(uuid, converter.convertFrom(uuid.toString()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromEmptyString() {

        converter.convertFrom("");
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromInvalid() {

        converter.convertFrom("Invalid UUID");
    }

    @Test
    public void testConvertTo() {

        UUID uuid = UUID.randomUUID();

        Assert.assertEquals(uuid.toString(), converter.convertTo(uuid));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }

    @Test
    public void testConvertFromParameterWithTrailingOrLeadingWhitespaces() {

        UUID uuid = UUID.randomUUID();

        String leadingSpace = String.format(" %s", uuid.toString());
        String trailingSpace = String.format("%s ", uuid.toString());
        String leadingAndTrailingSpace = String.format(" %s ", uuid.toString());

        Assert.assertEquals(uuid, converter.convertFrom(leadingSpace));
        Assert.assertEquals(uuid, converter.convertFrom(trailingSpace));
        Assert.assertEquals(uuid, converter.convertFrom(leadingAndTrailingSpace));
    }
}
