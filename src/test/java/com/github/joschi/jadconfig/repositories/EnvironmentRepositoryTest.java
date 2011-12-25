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
    public void testSave() throws RepositoryException {

        repository.save();
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
    public void testWrite() throws RepositoryException {

        repository.write("Test", "Value");
    }

    @Test
    public void testSize() throws RepositoryException {

        Assert.assertEquals(System.getenv().size(), repository.size());
    }
}