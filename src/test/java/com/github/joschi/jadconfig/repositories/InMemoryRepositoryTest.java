package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit tests for {@link InMemoryRepository}
 *
 * @author jschalanda
 */
public class InMemoryRepositoryTest {

    @Test
    public void testRead() throws RepositoryException {
        final InMemoryRepository emptyRepository = new InMemoryRepository();
        Assert.assertNull(emptyRepository.read("Test"));

        final InMemoryRepository inMemoryRepository = new InMemoryRepository(Collections.singletonMap("Test", "Value"));
        Assert.assertEquals("Value", inMemoryRepository.read("Test"));
    }

    @Test
    public void testConstructor() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("one", "one");
        map.put("two", "two");
        map.put("three", "three");

        Repository inMemoryRepository = new InMemoryRepository(map);

        Assert.assertEquals("one", inMemoryRepository.read("one"));
        Assert.assertEquals("two", inMemoryRepository.read("two"));
        Assert.assertEquals("three", inMemoryRepository.read("three"));
    }

    @Test
    public void testReadNames() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("opensearch.node.roles", "search");
        map.put("opensearch.node.name", "my-node");

        Repository inMemoryRepository = new InMemoryRepository(map);

        final Collection<String> names = inMemoryRepository.readNames("opensearch.");

        Assert.assertEquals(2, names.size());
        Assert.assertTrue(names.contains("opensearch.node.name"));
        Assert.assertTrue(names.contains("opensearch.node.roles"));
    }
}