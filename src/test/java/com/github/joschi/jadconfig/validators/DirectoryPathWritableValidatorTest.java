package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
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
 * Unit tests for {@link DirectoryPathWritableValidator}
 *
 * @author jschalanda
 */
public class DirectoryPathWritableValidatorTest {
    private DirectoryPathWritableValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new DirectoryPathWritableValidator();
    }

    @Test
    public void testExistingDirectory(@TempDir Path temp) throws ValidationException, IOException {
        validator.validate("Test", Files.createDirectories(temp.resolve("test")));
    }

    @Test
    public void testExistingFile(@TempDir Path temp) throws ValidationException, IOException {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", temp.resolve("test.txt"))
        );
    }

    @Test
    public void testMissingPermissions(@TempDir Path temp) {
        assertThrows(ValidationException.class,
                () -> {
                    File tempDir = Files.createDirectories(temp.resolve("other")).toFile();

                    if (!tempDir.setWritable(false)) {
                        fail("Couldn't set directory " + tempDir.getCanonicalPath() + " unwritable");
                    }

                    validator.validate("Test", tempDir.toPath());
                });

    }

    @Test
    public void testMissingDirectory() throws ValidationException, IOException {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", Paths.get("does-not-exist-" + System.currentTimeMillis()))
        );
    }

    @Test
    public void testNull() throws ValidationException, IOException {
        validator.validate("Test", null);
    }
}