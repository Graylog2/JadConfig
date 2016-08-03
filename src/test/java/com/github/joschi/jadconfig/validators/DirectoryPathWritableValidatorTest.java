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
 * Unit tests for {@link DirectoryPathWritableValidator}
 *
 * @author jschalanda
 */
public class DirectoryPathWritableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private DirectoryPathWritableValidator validator;

    @Before
    public void setUp() {
        validator = new DirectoryPathWritableValidator();
    }

    @Test
    public void testExistingDirectory() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFolder().toPath());
    }

    @Test(expected = ValidationException.class)
    public void testExistingFile() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFile().toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingPermissions() throws ValidationException, IOException {
        File tempDir = temporaryFolder.newFolder();
        if (!tempDir.setWritable(false)) {
            fail("Couldn't set directory " + tempDir.getCanonicalPath() + " unwritable");
        }

        validator.validate("Test", tempDir.toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingDirectory() throws ValidationException, IOException {
        validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()));
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}