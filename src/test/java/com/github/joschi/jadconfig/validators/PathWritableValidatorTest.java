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
 * Unit tests for {@link PathWritableValidator}
 *
 * @author jschalanda
 */
public class PathWritableValidatorTest {
    private PathWritableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PathWritableValidator();
    }

    @Test
    public void testExistingDirectory(@TempDir Path temp) throws ValidationException {
        validator.validate("Test", temp);
    }

    @Test
    public void testExistingFile(@TempDir Path temp) throws ValidationException, IOException {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", temp.resolve("test1.txt"))
        );
    }

    @Test
    public void testMissingFilePermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempFile = Files.createFile(temp.resolve("test1.txt")).toFile();
                    if (!tempFile.setWritable(false)) {
                        fail("Couldn't make file " + tempFile.getCanonicalPath() + " non-writable");
                    }

                    validator.validate("Test", tempFile.toPath());
                });
    }

    @Test
    public void testMissingDirectoryPermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempDir = Files.createDirectories(temp.resolve("testdir2")).toFile();
                    if (!tempDir.setWritable(false)) {
                        fail("Couldn't make directory " + tempDir.getCanonicalPath() + " non-writable");
                    }

                    validator.validate("Test", tempDir.toPath());
                });
    }

    @Test
    public void testCorrectFilePermissions(@TempDir Path temp) throws IOException, ValidationException {
        File tempFile = Files.createFile(temp.resolve("test2.txt")).toFile();
        if (!tempFile.setWritable(true)) {
            fail("Couldn't make file " + tempFile.getCanonicalPath() + " writable");
        }

        validator.validate("Test", tempFile.toPath());
    }

    @Test
    public void testCorrectDirectoryPermissions(@TempDir Path temp) throws IOException, ValidationException {
        File tempDir = Files.createDirectories(temp.resolve("testdir2")).toFile();
        if (!tempDir.setWritable(true)) {
            fail("Couldn't make directory " + tempDir.getCanonicalPath() + " writable");
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