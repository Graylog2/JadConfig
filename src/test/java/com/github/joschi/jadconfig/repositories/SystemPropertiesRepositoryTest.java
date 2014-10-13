package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void testClose() throws RepositoryException {

        repository.close();
    }

    @Test
    public void testRead() throws RepositoryException {

        Assert.assertNull(repository.read("This system property should not exist"));
        Assert.assertEquals(System.getProperty("java.version"), repository.read("java.version"));
    }

    @Test
    public void testReadWithPrefix() throws RepositoryException {
        final SystemPropertiesRepository testRepository = new SystemPropertiesRepository("java.");

        Assert.assertEquals(System.getProperty("java.version"), testRepository.read("version"));
    }

    @Test
    public void testSize() throws RepositoryException {

        Assert.assertEquals(System.getProperties().size(), repository.size());
    }
}