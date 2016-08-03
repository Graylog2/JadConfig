package com.github.joschi.jadconfig.validators;


import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Unit tests for {@link FileWritableValidator}
 *
 * @author jschalanda
 */
public class FileWritableValidatorTest {

    private FileWritableValidator validator;

    @Before
    public void setUp() {
        validator = new FileWritableValidator();
    }

    @Test
    public void testExistingFile() throws ValidationException, IOException {
        File tempFile = File.createTempFile("FileWritableValidatorTest-testExistingFile", ".tmp");
        tempFile.deleteOnExit();

        validator.validate("Test", tempFile);
    }

    @Test(expected = ValidationException.class)
    public void testMissingPermissions() throws ValidationException, IOException {
        File tempFile = File.createTempFile("FileWritableValidatorTest-testMissingPermissions", ".tmp");
        tempFile.deleteOnExit();

        if (!tempFile.setWritable(false)) {
            fail("Couldn't set file " + tempFile.getCanonicalPath() + " unwritable");
        }

        validator.validate("Test", tempFile);
    }

    @Test(expected = ValidationException.class)
    public void testMissingFile() throws ValidationException, IOException {
        File tempFile = File.createTempFile("FileWritableValidatorTest-testMissingFile", ".tmp");

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