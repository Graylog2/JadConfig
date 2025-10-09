package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link DirectoryWritableValidator}
 *
 * @author jschalanda
 */
public class DirectoryWritableValidatorTest {

    private DirectoryWritableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new DirectoryWritableValidator();
    }

    @Test
    public void testExistingDirectory() throws ValidationException, IOException {
        File tempDir = Files.createTempDirectory("DirectoryWritableValidatorTest-testExistingDirectory" + ".dir").toFile();

        tempDir.deleteOnExit();

        validator.validate("Test", tempDir);
    }

    @Test
    public void testExistingFile() {
        assertThrows(ValidationException.class,
                () -> {
                    File tmpFile = File.createTempFile("DirectoryWritableValidatorTest-testExistingFile", ".tmp");
                    tmpFile.deleteOnExit();

                    validator.validate("Test", tmpFile);
                });
    }

    @Test
    public void testMissingPermissions() {
        assertThrows(ValidationException.class,
                () -> {
                    File tempDir = Files.createTempDirectory("DirectoryWritableValidatorTest-testMissingPermissions" + ".dir").toFile();

                    if (!tempDir.setWritable(false)) {
                        fail("Couldn't set directory " + tempDir.getCanonicalPath() + " unwritable");
                    }

                    validator.validate("Test", tempDir);
                });
    }

    @Test
    public void testMissingDirectory() {
        assertThrows(ValidationException.class,
                () -> {
                    File tempFile = File.createTempFile("DirectoryWritableValidatorTest-testMissingDirectory", ".tmp");


                    if (!tempFile.delete()) {
                        fail("Couldn't delete temporary file " + tempFile.getCanonicalPath());
                    }

                    validator.validate("Test", tempFile);
                });
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}
