package com.github.joschi.jadconfig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link RepositoryException}
 *
 * @author jschalanda
 */
public class RepositoryExceptionTest {

    RepositoryException exception;

    @Test
    public void testConstructor() {

        exception = new RepositoryException();

        Assert.assertNull(exception.getMessage());
        Assert.assertNull(exception.getCause());

        exception = new RepositoryException("Test");

        Assert.assertEquals("Test", exception.getMessage());
        Assert.assertNull(exception.getCause());

        Exception cause = new Exception("Cause");
        exception = new RepositoryException("Test", cause);

        Assert.assertEquals("Test", exception.getMessage());
        Assert.assertEquals(cause, exception.getCause());
    }

    @Test(expected = RepositoryException.class)
    public void testException() throws RepositoryException {

        throw new RepositoryException("Test");
    }
}
