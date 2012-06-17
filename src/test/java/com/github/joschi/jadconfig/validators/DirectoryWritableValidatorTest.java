package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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

        File tempDir = File.createTempFile("DirectoryWritableValidatorTest-testExistingDirectory", ".dir");
        if (!tempDir.delete()) {
            fail("Couldn't delete temporary file " + tempDir.getCanonicalPath());
        }

        if (!tempDir.mkdir()) {
            fail("Couldn't create temporary directory " + tempDir.getCanonicalPath());
        }

        tempDir.deleteOnExit();

        validator.validate("Test", tempDir.getAbsolutePath());
    }

    @Test(expected = ValidationException.class)
    public void testExistingFile() throws ValidationException, IOException {

        File tmpFile = File.createTempFile("DirectoryWritableValidatorTest-testExistingFile", ".tmp");
        tmpFile.deleteOnExit();

        validator.validate("Test", tmpFile.getAbsolutePath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingPermissions() throws ValidationException, IOException {

        File tempDir = File.createTempFile("DirectoryWritableValidatorTest-testMissingPermissions", ".dir");
        if (!tempDir.delete()) {
            fail("Couldn't delete temporary file " + tempDir.getCanonicalPath());
        }

        if (!tempDir.mkdir()) {
            fail("Couldn't create temporary directory " + tempDir.getCanonicalPath());
        }

        if (!tempDir.setWritable(false)) {
            fail("Couldn't set directory " + tempDir.getCanonicalPath() + " unwritable");
        }

        validator.validate("Test", tempDir.getAbsolutePath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingDirectory() throws ValidationException, IOException {

        File tempFile = File.createTempFile("DirectoryWritableValidatorTest-testMissingDirectory", ".tmp");

        if (!tempFile.delete()) {
            fail("Couldn't delete temporary file " + tempFile.getCanonicalPath());
        }

        validator.validate("Test", tempFile.getAbsolutePath());
    }

    @Test(expected = ValidationException.class)
    public void testNull() throws ValidationException, IOException {

        validator.validate("Test", null);
    }

    @Test(expected = ValidationException.class)
    public void testEmpty() throws ValidationException, IOException {

        validator.validate("Test", "");
    }
}