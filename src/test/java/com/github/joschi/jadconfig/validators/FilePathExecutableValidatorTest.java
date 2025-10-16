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
 * Unit tests for {@link FilePathExecutableValidator}
 *
 * @author jschalanda
 */
public class FilePathExecutableValidatorTest {
    private FilePathExecutableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new FilePathExecutableValidator();
    }

    @Test
    public void testMissingPermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempFile = Files.createFile(temp.resolve("test1.bin")).toFile();

                    if (!tempFile.setExecutable(false)) {
                        fail("Couldn't make file " + tempFile.getCanonicalPath() + " non-executable");
                    }

                    validator.validate("Test", tempFile.toPath());
                });
    }

    @Test
    public void testCorrectPermissions(@TempDir Path temp) throws ValidationException, IOException {
        File tempFile = Files.createFile(temp.resolve("test1.bin")).toFile();
        if (!tempFile.setExecutable(true)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " executable");
        }

        validator.validate("Test", tempFile.toPath());
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