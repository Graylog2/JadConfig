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
 * Unit tests for {@link DirectoryPathReadableValidator}
 *
 * @author jschalanda
 */
public class DirectoryPathReadableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private DirectoryPathReadableValidator validator;

    @Before
    public void setUp() {
        validator = new DirectoryPathReadableValidator();
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
    public void testUnreadableDirectory() throws ValidationException, IOException {
        File tempDir = temporaryFolder.newFolder();
        if (!tempDir.setReadable(false)) {
            fail("Couldn't set directory " + tempDir + " unreadable");
        }

        validator.validate("Test", tempDir.toPath());
    }

    @Test(expected = ValidationException.class)
    public void testMissingDirectory() throws ValidationException, IOException {
        validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()));
    }

    @Test(expected = ValidationException.class)
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}