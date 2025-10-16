package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link InMemoryRepository}
 *
 * @author jschalanda
 */public class InMemoryRepositoryTest {

    private InMemoryRepository repository;

    @BeforeEach
    public void setUp() {

        repository = new InMemoryRepository();
    }

    @Test
    public void testOpen() throws RepositoryException {

        repository.open();

        Assertions.assertEquals(0, repository.size());
    }

    @Test
    public void testCloseWithoutOpen() {
        assertThrows(IllegalStateException.class,
                () -> repository.close()
        );
    }

    @Test
    public void testClose() throws RepositoryException {

        repository.open();
        Assertions.assertEquals(0, repository.size());
        repository.close();

        try {
            repository.size();
            fail("repository.size() should throw an IllegalStateException");
        } catch (IllegalStateException ex) {
            // OK
        }
    }

    @Test
    public void testReadWithoutOpen() {
        assertThrows(IllegalStateException.class,
                () -> repository.read("Test")
        );
    }

    @Test
    public void testRead() throws RepositoryException {

        final InMemoryRepository emptyRepository = new InMemoryRepository();
        emptyRepository.open();
        Assertions.assertNull(emptyRepository.read("Test"));

        final InMemoryRepository inMemoryRepository = new InMemoryRepository(Collections.singletonMap("Test", "Value"));
        inMemoryRepository.open();
        Assertions.assertEquals("Value", inMemoryRepository.read("Test"));
    }


    @Test
    public void testSizeWithoutOpen() {
        assertThrows(IllegalStateException.class,
                () -> repository.size()
        );
    }

    @Test
    public void testSize() throws RepositoryException {

        final InMemoryRepository emptyRepository = new InMemoryRepository();
        emptyRepository.open();
        Assertions.assertEquals(0, emptyRepository.size());

        final InMemoryRepository inMemoryRepository = new InMemoryRepository(Collections.singletonMap("Test", "Value"));
        inMemoryRepository.open();
        Assertions.assertEquals(1, inMemoryRepository.size());
    }

    @Test
    public void testConstructor() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("one", "one");
        map.put("two", "two");
        map.put("three", "three");

        InMemoryRepository inMemoryRepository = new InMemoryRepository(map);

        Assertions.assertEquals("one", inMemoryRepository.read("one"));
        Assertions.assertEquals("two", inMemoryRepository.read("two"));
        Assertions.assertEquals("three", inMemoryRepository.read("three"));
    }
}