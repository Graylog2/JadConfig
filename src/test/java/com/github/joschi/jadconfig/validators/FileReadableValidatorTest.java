package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Unit tests for {@link FileReadableValidator}
 *
 * @author jschalanda
 */
public class FileReadableValidatorTest {

    private FileReadableValidator validator;

    @Before
    public void setUp() {
        validator = new FileReadableValidator();
    }

    @Test
    public void testExistingFile() throws ValidationException, IOException {
        File tempFile = File.createTempFile("FileReadableValidatorTest-testExistingFile", ".tmp");
        tempFile.deleteOnExit();

        validator.validate("Test", tempFile);
    }

    @Test(expected = ValidationException.class)
    public void testUnreadableFile() throws ValidationException, IOException {
        File tempFile = File.createTempFile("FileReadableValidatorTest-testMissingPermissions", ".tmp");
        tempFile.deleteOnExit();

        if (!tempFile.setReadable(false)) {
            fail("Couldn't set file " + tempFile.getCanonicalPath() + " unreadable");
        }

        validator.validate("Test", tempFile);
    }

    @Test(expected = ValidationException.class)
    public void testMissingFile() throws ValidationException, IOException {
        File tempFile = File.createTempFile("FileReadableValidatorTest-testMissingFile", ".tmp");

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