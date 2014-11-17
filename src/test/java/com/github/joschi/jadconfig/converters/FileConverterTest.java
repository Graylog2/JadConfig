package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Unit tests for {@link FileConverter}
 *
 * @author jschalanda
 */
public class FileConverterTest {

    private FileConverter converter;

    @Before
    public void setUp() {

        converter = new FileConverter();
    }

    @Test
    public void testConvertFrom() throws IOException {

        File tmpFile = File.createTempFile("FileConverterTest-testConvert", ".tmp");
        tmpFile.deleteOnExit();

        Assert.assertEquals(new File(""), converter.convertFrom(""));
        Assert.assertEquals(tmpFile, converter.convertFrom(tmpFile.getPath()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertFromNull() {

        converter.convertFrom(null);
    }

    @Test
    public void testConvertTo() throws IOException {

        File tmpFile = File.createTempFile("FileConverterTest-testConvert", ".tmp");
        tmpFile.deleteOnExit();

        Assert.assertEquals("", converter.convertTo(new File("")));
        Assert.assertEquals(tmpFile.getPath(), converter.convertTo(tmpFile));
    }

    @Test(expected = ParameterException.class)
    public void testConvertToNull() {

        converter.convertTo(null);
    }
}
