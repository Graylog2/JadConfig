package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link FileConverter}
 *
 * @author jschalanda
 */
public class FileConverterTest {

    private FileConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new FileConverter();
    }

    @Test
    public void testConvertFrom() throws IOException {

        File tmpFile = File.createTempFile("FileConverterTest-testConvert", ".tmp");
        tmpFile.deleteOnExit();

        Assertions.assertEquals(new File(""), converter.convertFrom(""));
        Assertions.assertEquals(tmpFile, converter.convertFrom(tmpFile.getPath()));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() throws IOException {

        File tmpFile = File.createTempFile("FileConverterTest-testConvert", ".tmp");
        tmpFile.deleteOnExit();

        Assertions.assertEquals("", converter.convertTo(new File("")));
        Assertions.assertEquals(tmpFile.getPath(), converter.convertTo(tmpFile));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
