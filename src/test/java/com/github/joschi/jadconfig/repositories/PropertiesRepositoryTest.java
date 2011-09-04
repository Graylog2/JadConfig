package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.fail;

/**
 * Unit tests for {@link PropertiesRepository}
 *
 * @author jschalanda
 */
public class PropertiesRepositoryTest {

    private static final String PROPERTIES_FILE = PropertiesRepository.class.getResource("/testConfiguration.properties").getFile();

    private PropertiesRepository repository;

    @Before
    public void setUp() {

        repository = new PropertiesRepository(PROPERTIES_FILE);
    }

    @Test
    public void testConstructorFilename() throws RepositoryException {

        repository = new PropertiesRepository(PROPERTIES_FILE);
        Assert.assertEquals(PROPERTIES_FILE, repository.getPropertiesFile().getPath());

        repository.open();

        Assert.assertEquals("Test", repository.read("test.string"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullFilename() {

        new PropertiesRepository((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullEmpty() {

        new PropertiesRepository("");
    }

    @Test
    public void testConstructorFile() throws RepositoryException {

        repository = new PropertiesRepository(new File(PROPERTIES_FILE));
        Assert.assertEquals(PROPERTIES_FILE, repository.getPropertiesFile().getPath());

        repository.open();

        Assert.assertEquals("Test", repository.read("test.string"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullFile() {

        new PropertiesRepository((File) null);
    }

    @Test(expected = RepositoryException.class)
    public void testOpenNonExistingFile() throws RepositoryException, IOException {

        File file = File.createTempFile("PropertiesRepositoryTest-testOpenNonExistingFile", ".properties");

        if (!file.delete()) {

            fail(file.getCanonicalPath() + " already exists and can't be deleted. Test prerequisite not met!");
        }

        repository = new PropertiesRepository(file);

        repository.open();
    }

    @Test
    public void testOpen() throws RepositoryException {

        repository.open();

        Assert.assertEquals("Test", repository.read("test.string"));
        Assert.assertEquals("123456", repository.read("test.integer"));
    }

    @Test
    public void testClose() throws RepositoryException {

        repository.open();
        repository.close();
    }

    @Test
    public void testWrite() throws RepositoryException {

        repository.open();
        repository.write("Adhoc", "Value");

        Assert.assertEquals("Value", repository.read("Adhoc"));
    }

    @Test
    public void testSave() throws RepositoryException, IOException {

        File file = File.createTempFile("PropertiesRepositoryTest-testSave", ".properties");
        file.deleteOnExit();

        repository = new PropertiesRepository(file);

        repository.open();
        repository.write("Adhoc", "Value");
        repository.save();
        repository.close();

        Properties properties = new Properties();
        properties.load(new FileReader(file));

        Assert.assertEquals("Value", properties.getProperty("Adhoc"));
    }
}
