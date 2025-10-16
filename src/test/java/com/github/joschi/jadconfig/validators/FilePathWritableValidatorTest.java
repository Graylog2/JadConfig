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
 * Unit tests for {@link FilePathWritableValidator}
 *
 * @author jschalanda
 */
public class FilePathWritableValidatorTest {
    private FilePathWritableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new FilePathWritableValidator();
    }

    @Test
    public void testExistingFile(@TempDir Path temp) throws ValidationException, IOException {
        var tempFile = Files.createFile(temp.resolve("test0.txt"));
        validator.validate("Test", tempFile);
    }

    @Test
    public void testMissingPermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempFile = Files.createFile(temp.resolve("test1.txt")).toFile();

                    if (!tempFile.setWritable(false)) {
                        fail("Couldn't set file " + tempFile.getCanonicalPath() + " unwritable");
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