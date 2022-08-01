package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.fail;

/**
 * Unit tests for {@link DirectoryWritableValidator}
 *
 * @author jschalanda
 */
public class DirectoryWritableValidatorTest {

    private DirectoryWritableValidator validator;

    @Before
    public void setUp() {
        validator = new DirectoryWritableValidator();
    }

    @Test
    public void testExistingDirectory() throws ValidationException, IOException {
        File tempDir = Files.createTempDirectory("DirectoryWritableValidatorTest-testExistingDirectory" + ".dir").toFile();

        tempDir.deleteOnExit();

        validator.validate("Test", tempDir);
    }

    @Test(expected = ValidationException.class)
    public void testExistingFile() throws ValidationException, IOException {
        File tmpFile = File.createTempFile("DirectoryWritableValidatorTest-testExistingFile", ".tmp");
        tmpFile.deleteOnExit();

        validator.validate("Test", tmpFile);
    }

    @Test(expected = ValidationException.class)
    public void testMissingPermissions() throws ValidationException, IOException {
        File tempDir = Files.createTempDirectory("DirectoryWritableValidatorTest-testMissingPermissions" + ".dir").toFile();

        if (!tempDir.setWritable(false)) {
            fail("Couldn't set directory " + tempDir.getCanonicalPath() + " unwritable");
        }

        validator.validate("Test", tempDir);
    }

    @Test(expected = ValidationException.class)
    public void testMissingDirectory() throws ValidationException, IOException {
        File tempFile = File.createTempFile("DirectoryWritableValidatorTest-testMissingDirectory", ".tmp");

        if (!tempFile.delete()) {
            fail("Couldn't delete temporary file " + tempFile.getCanonicalPath());
        }

        validator.validate("Test", tempFile);
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}
