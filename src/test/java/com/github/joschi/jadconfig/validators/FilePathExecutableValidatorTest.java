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
 * Unit tests for {@link FilePathExecutableValidator}
 *
 * @author jschalanda
 */
public class FilePathExecutableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private FilePathExecutableValidator validator;

    @Before
    public void setUp() {
        validator = new FilePathExecutableValidator();
    }

    @Test(expected = ValidationException.class)
    public void testMissingPermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setExecutable(false)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " non-executable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test
    public void testCorrectPermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setExecutable(true)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " executable");
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