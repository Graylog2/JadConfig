package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link PathConverter}
 *
 * @author jschalanda
 */
public class PathConverterTest {

    private PathConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new PathConverter();
    }

    @Test
    public void testConvertFrom() throws IOException {

        Path tmpFile = Files.createTempFile("PathConverterTest-testConvertFrom", ".tmp");
        tmpFile.toFile().deleteOnExit();

        Assertions.assertEquals(Paths.get(""), converter.convertFrom(""));
        Assertions.assertEquals(tmpFile, converter.convertFrom(tmpFile.toString()));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertTo() throws IOException {

        Path tmpFile = Files.createTempFile("PathConverterTest-testConvertTo", ".tmp");
        tmpFile.toFile().deleteOnExit();

        Assertions.assertEquals("", converter.convertTo(Paths.get("")));
        Assertions.assertEquals(tmpFile.toString(), converter.convertTo(tmpFile));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
