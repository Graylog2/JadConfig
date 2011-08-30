package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.ParameterException;
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

    FileConverter converter;

    @Before
    public void setUp() {

        converter = new FileConverter();
    }

    @Test
    public void testConvert() throws IOException {

        File tmpFile = File.createTempFile("FileConverterTest-testConvert", ".tmp");
        tmpFile.deleteOnExit();

        Assert.assertEquals(new File(""), converter.convert(""));
        Assert.assertEquals(tmpFile, converter.convert(tmpFile.getCanonicalPath()));
    }

    @Test(expected = ParameterException.class)
    public void testConvertNull() {

        converter.convert(null);
    }
}
