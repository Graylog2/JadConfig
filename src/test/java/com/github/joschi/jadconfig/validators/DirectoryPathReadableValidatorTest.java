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
 * Unit tests for {@link DirectoryPathReadableValidator}
 *
 * @author jschalanda
 */
public class DirectoryPathReadableValidatorTest {
    private DirectoryPathReadableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new DirectoryPathReadableValidator();
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
    public void testUnreadableDirectory(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempDir = Files.createDirectories(temp.resolve("testdir1")).toFile();
                    if (!tempDir.setReadable(false)) {
                        fail("Couldn't set directory " + tempDir + " unreadable");
                    }

                    validator.validate("Test", tempDir.toPath());
                });
    }

    @Test
    public void testMissingDirectory() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()))
        );
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}