package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link PropertiesRepository}
 *
 * @author jschalanda
 */
public class PropertiesRepositoryTest {

    private static final String PROPERTIES_FILE = PropertiesRepository.class.getResource("/testConfiguration.properties").getFile();

    private PropertiesRepository repository;

    @BeforeEach
    public void setUp() {

        repository = new PropertiesRepository(PROPERTIES_FILE);
    }

    @Test
    public void testConstructorFilename() throws RepositoryException {

        repository = new PropertiesRepository(PROPERTIES_FILE);
        Assertions.assertEquals(PROPERTIES_FILE, repository.getPropertiesFile().getPath());

        repository.open();

        Assertions.assertEquals("Test", repository.read("test.string"));
    }

    @Test
    public void testConstructorNullFilename() {
        assertThrows(IllegalArgumentException.class,
                () -> new PropertiesRepository((String) null)
        );
    }

    @Test
    public void testConstructorNullEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new PropertiesRepository("")
        );
    }

    @Test
    public void testConstructorFile() throws RepositoryException {

        repository = new PropertiesRepository(new File(PROPERTIES_FILE));
        Assertions.assertEquals(PROPERTIES_FILE, repository.getPropertiesFile().getPath());

        repository.open();

        Assertions.assertEquals("Test", repository.read("test.string"));
    }

    @Test
    public void testConstructorNullFile() {
        assertThrows(IllegalArgumentException.class,
                () -> new PropertiesRepository((File) null)
        );
    }

    @Test
    public void testOpenNonExistingFile() throws RepositoryException, IOException {
        assertThrows(RepositoryException.class,
                () -> {
                    File file = File.createTempFile("PropertiesRepositoryTest-testOpenNonExistingFile", ".properties");

                    if (!file.delete()) {

                        fail(file.getCanonicalPath() + " already exists and can't be deleted. Test prerequisite not met!");
                    }

                    repository = new PropertiesRepository(file);

                    repository.open();
                });
    }

    @Test
    public void testOpen() throws RepositoryException {

        repository.open();

        Assertions.assertEquals("Test", repository.read("test.string"));
        Assertions.assertEquals("123456", repository.read("test.integer"));
    }

    @Test
    public void testClose() throws RepositoryException {

        repository.open();
        repository.close();
    }
}
