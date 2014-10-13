package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link EnvironmentRepository}
 *
 * @author jschalanda
 */
public class EnvironmentRepositoryTest {

    private EnvironmentRepository repository;

    @Before
    public void setUp() {

        repository = new EnvironmentRepository();
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

        Assert.assertNull(repository.read("This environment variable should not exist"));

        Assert.assertEquals(System.getenv("JAVA_HOME"), repository.read("JAVA_HOME"));
    }

    @Test
    public void testUpperCaseEnabledRead() throws RepositoryException {
        final EnvironmentRepository testRepository = new EnvironmentRepository(true);

        Assert.assertEquals(System.getenv("JAVA_HOME"), testRepository.read("jAvA_homE"));
    }

    @Test
    public void testUpperCaseDisabledRead() throws RepositoryException {
        final EnvironmentRepository testRepository = new EnvironmentRepository(false);

        Assert.assertNull(testRepository.read("jAvA_homE"));
        Assert.assertEquals(System.getenv("JAVA_HOME"), testRepository.read("JAVA_HOME"));
    }

    @Test
    public void testPrefixedRead() throws RepositoryException {
        final EnvironmentRepository testRepository = new EnvironmentRepository("JAVA_");

        Assert.assertEquals(System.getenv("JAVA_HOME"), testRepository.read("HOME"));
    }

    @Test
    public void testSize() throws RepositoryException {

        Assert.assertEquals(System.getenv().size(), repository.size());
    }
}