package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SystemPropertiesRepository}
 *
 * @author jschalanda
 */
public class SystemPropertiesRepositoryTest {

    private SystemPropertiesRepository repository;

    @BeforeEach
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

        Assertions.assertNull(repository.read("This system property should not exist"));
        Assertions.assertEquals(System.getProperty("java.version"), repository.read("java.version"));
    }

    @Test
    public void testReadWithPrefix() throws RepositoryException {
        final SystemPropertiesRepository testRepository = new SystemPropertiesRepository("java.");

        Assertions.assertEquals(System.getProperty("java.version"), testRepository.read("version"));
    }

    @Test
    public void testSize() throws RepositoryException {

        Assertions.assertEquals(System.getProperties().size(), repository.size());
    }
}