package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link FilePathReadableValidator}
 *
 * @author jschalanda
 */
public class FilePathReadableValidatorTest {
    private FilePathReadableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new FilePathReadableValidator();
    }

    @Test
    public void testExistingFile(@TempDir Path temp) throws ValidationException, IOException {
        validator.validate("Test", Files.createFile(temp.resolve("test.txt")));
    }

    @Test
    public void testUnreadableFile(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempFile = Files.createFile(temp.resolve("test1.txt")).toFile();

                    if (!tempFile.setReadable(false)) {
                        fail("Couldn't set file " + tempFile.getCanonicalPath() + " unreadable");
                    }

                    validator.validate("Test", tempFile.toPath());
                });
    }

    @Test
    public void testMissingFile() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()))
        );
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}