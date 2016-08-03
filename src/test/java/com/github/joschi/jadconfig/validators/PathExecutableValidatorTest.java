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
 * Unit tests for {@link PathExecutableValidator}
 *
 * @author jschalanda
 */
public class PathExecutableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private PathExecutableValidator validator;

    @Before
    public void setUp() {
        validator = new PathExecutableValidator();
    }

    @Test
    public void testExistingDirectory() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFolder().toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingFilePermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setExecutable(false)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " non-executable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingDirectoryPermissions() throws ValidationException, IOException {
        File tempDir = temporaryFolder.newFolder();
        if (!tempDir.setExecutable(false)) {
            fail("Couldn't make directory " + tempDir.getCanonicalPath() + " non-executable");
        }

        validator.validate("Test", tempDir.toPath());
    }

    @Test
    public void testCorrectFilePermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setExecutable(true)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " non-executable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test
    public void testCorrectDirectoryPermissions() throws ValidationException, IOException {
        File tempDir = temporaryFolder.newFolder();
        if (!tempDir.setExecutable(true)) {
            fail("Couldn't make directory " + tempDir.getCanonicalPath() + " non-executable");
        }

        validator.validate("Test", tempDir.toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingFile() throws ValidationException, IOException {
        validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()));
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}