package com.github.joschi.jadconfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link RepositoryException}
 *
 * @author jschalanda
 */
public class RepositoryExceptionTest {

    private RepositoryException exception;

    @Test
    public void testConstructor() {

        exception = new RepositoryException();

        Assertions.assertNull(exception.getMessage());
        Assertions.assertNull(exception.getCause());

        exception = new RepositoryException("Test");

        Assertions.assertEquals("Test", exception.getMessage());
        Assertions.assertNull(exception.getCause());

        Exception cause = new Exception("Cause");
        exception = new RepositoryException("Test", cause);

        Assertions.assertEquals("Test", exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
    }

    @Test
    public void testException() {
        assertThrows(RepositoryException.class,
                () -> {
                    throw new RepositoryException("Test");
                });
    }
}
