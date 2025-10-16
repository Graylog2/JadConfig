package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link EnvironmentRepository}
 *
 * @author jschalanda
 */
public class EnvironmentRepositoryTest {

    private EnvironmentRepository repository;

    @BeforeEach
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

        Assertions.assertNull(repository.read("This environment variable should not exist"));

        Assertions.assertEquals(System.getenv("JAVA_HOME"), repository.read("JAVA_HOME"));
    }

    @Test
    public void testUpperCaseEnabledRead() throws RepositoryException {
        final EnvironmentRepository testRepository = new EnvironmentRepository(true);

        Assertions.assertEquals(System.getenv("JAVA_HOME"), testRepository.read("jAvA_homE"));
    }

    @Test
    public void testUpperCaseDisabledRead() throws RepositoryException {
        final EnvironmentRepository testRepository = new EnvironmentRepository(false);

        Assertions.assertNull(testRepository.read("jAvA_homE"));
        Assertions.assertEquals(System.getenv("JAVA_HOME"), testRepository.read("JAVA_HOME"));
    }

    @Test
    public void testPrefixedRead() throws RepositoryException {
        final EnvironmentRepository testRepository = new EnvironmentRepository("JAVA_");

        Assertions.assertEquals(System.getenv("JAVA_HOME"), testRepository.read("HOME"));
    }

    @Test
    public void testSize() throws RepositoryException {

        Assertions.assertEquals(System.getenv().size(), repository.size());
    }
}