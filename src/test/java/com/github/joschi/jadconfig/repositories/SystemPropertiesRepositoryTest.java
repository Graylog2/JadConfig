package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Unit tests for {@link SystemPropertiesRepository}
 *
 * @author jschalanda
 */
public class SystemPropertiesRepositoryTest {

    private SystemPropertiesRepository repository;

    @Before
    public void setUp() {

        repository = new SystemPropertiesRepository();
    }

    @Test
    public void testOpen() throws RepositoryException {

        repository.open();
    }

    @Test
    public void testSave() throws RepositoryException {

        repository.save();
    }

    @Test
    public void testClose() throws RepositoryException {

        repository.close();
    }

    @Test
    public void testRead() throws RepositoryException {

        Assert.assertNull(repository.read("This system property should not exist"));
        Map m = System.getProperties();
        Assert.assertEquals(System.getProperty("java.version"), repository.read("java.version"));
    }

    @Test
    public void testWrite() throws RepositoryException {

        repository.write("Test", "Value");
    }

    @Test
    public void testSize() throws RepositoryException {

        Assert.assertEquals(System.getProperties().size(), repository.size());
    }
}