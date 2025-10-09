package com.github.joschi.jadconfig.validators;


import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link PathReadableValidator}
 *
 * @author jschalanda
 */
public class PathReadableValidatorTest {
    private PathReadableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PathReadableValidator();
    }

    @Test
    public void testExistingFile(@TempDir Path temp) throws ValidationException, IOException {
        var tempFile = Files.createFile(temp.resolve("test1.txt"));
        validator.validate("Test", tempFile);
    }

    @Test
    public void testExistingDirectory(@TempDir Path temp) throws ValidationException {
        validator.validate("Test", temp);
    }

    @Test
    public void testMissingFilePermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempFile = Files.createFile(temp.resolve("test2.txt")).toFile();

                    if (!tempFile.setReadable(false)) {
                        fail("Couldn't make file " + tempFile.getCanonicalPath() + " unreadable");
                    }

                    validator.validate("Test", tempFile.toPath());
                });
    }

    @Test
    public void testMissingDirectoryPermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempDir = Files.createDirectories(temp.resolve("testdir1")).toFile();
                    if (!tempDir.setReadable(false)) {
                        fail("Couldn't make directory " + tempDir.getCanonicalPath() + " unreadable");
                    }

                    validator.validate("Test", tempDir.toPath());
                });
    }

    @Test
    public void testCorrectFilePermissions(@TempDir Path temp) throws ValidationException, IOException {
        File tempFile = Files.createFile(temp.resolve("test3.txt")).toFile();
        if (!tempFile.setReadable(true)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " readable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test
    public void testCorrectDirectoryPermissions(@TempDir Path temp) throws ValidationException, IOException {
        File tempDir = Files.createDirectories(temp.resolve("testdir2")).toFile();
        if (!tempDir.setReadable(true)) {
            fail("Couldn't make directory " + tempDir.getCanonicalPath() + " readable");
        }

        validator.validate("Test", tempDir.toPath());
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