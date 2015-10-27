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
 * Unit tests for {@link PathReadableValidator}
 *
 * @author jschalanda
 */
public class PathReadableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private PathReadableValidator validator;

    @Before
    public void setUp() {
        validator = new PathReadableValidator();
    }

    @Test
    public void testExistingFile() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFile().toPath());
    }

    @Test
    public void testExistingDirectory() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFolder().toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingFilePermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setReadable(false)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " unreadable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingDirectoryPermissions() throws ValidationException, IOException {
        File tempDir = temporaryFolder.newFolder();
        if (!tempDir.setReadable(false)) {
            fail("Couldn't make directory " + tempDir.getCanonicalPath() + " unreadable");
        }

        validator.validate("Test", tempDir.toPath());
    }

    @Test
    public void testCorrectFilePermissions() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setReadable(true)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " readable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test
    public void testCorrectDirectoryPermissions() throws ValidationException, IOException {
        File tempDir = temporaryFolder.newFolder();
        if (!tempDir.setReadable(true)) {
            fail("Couldn't make directory " + tempDir.getCanonicalPath() + " readable");
        }

        validator.validate("Test", tempDir.toPath());
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