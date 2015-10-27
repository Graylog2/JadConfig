package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit tests for {@link PathConverter}
 *
 * @author jschalanda
 */
public class PathConverterTest {

    private PathConverter converter;

    @Before
    public void setUp() {

        converter = new PathConverter();
    }

    @Test
    public void testConvertFrom() throws IOException {

        Path tmpFile = Files.createTempFile("PathConverterTest-testConvertFrom", ".tmp");
        tmpFile.toFile().deleteOnExit();

        Assert.assertEquals(Paths.get(""), converter.convertFrom(""));
        Assert.assertEquals(tmpFile, converter.convertFrom(tmpFile.toString()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() throws IOException {

        Path tmpFile = Files.createTempFile("PathConverterTest-testConvertTo", ".tmp");
        tmpFile.toFile().deleteOnExit();

        Assert.assertEquals("", converter.convertTo(Paths.get("")));
        Assert.assertEquals(tmpFile.toString(), converter.convertTo(tmpFile));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
