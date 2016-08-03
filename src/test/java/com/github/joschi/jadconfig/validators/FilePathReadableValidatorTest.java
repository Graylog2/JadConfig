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
 * Unit tests for {@link FilePathReadableValidator}
 *
 * @author jschalanda
 */
public class FilePathReadableValidatorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private FilePathReadableValidator validator;

    @Before
    public void setUp() {
        validator = new FilePathReadableValidator();
    }

    @Test
    public void testExistingFile() throws ValidationException, IOException {
        validator.validate("Test", temporaryFolder.newFile().toPath());
    }

    @Test(expected = ValidationException.class)
    public void testUnreadableFile() throws ValidationException, IOException {
        File tempFile = temporaryFolder.newFile();
        if (!tempFile.setReadable(false)) {
            fail("Couldn't set file " + tempFile.getCanonicalPath() + " unreadable");
        }

        validator.validate("Test", tempFile.toPath());
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