package com.github.joschi.jadconfig.validators;


import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.fail;

/**
 * Unit tests for {@link FilePathWritableValidator}
 *
 * @author jschalanda
 */
public class FilePathWritableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private FilePathWritableValidator validator;

    @Before
    public void setUp() {
        validator = new FilePathWritableValidator();
    }

    @Test
    public void testExistingFile() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFile().toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingPermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setWritable(false)) {
            fail("Couldn't set file " + tempFile.getCanonicalPath() + " unwritable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingFile() throws ValidationException, IOException {
        validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()));
    }

    @Test(expected = ValidationException.class)
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}