package com.github.joschi.jadconfig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ValidationException}
 *
 * @author jschalanda
 */
public class ValidationExceptionTest {

    ValidationException exception;

    @Test
    public void testConstructor() {

        exception = new ValidationException();

        Assert.assertNull(exception.getMessage());
        Assert.assertNull(exception.getCause());

        exception = new ValidationException("Test");

        Assert.assertEquals("Test", exception.getMessage());
        Assert.assertNull(exception.getCause());

        Exception cause = new Exception("Cause");
        exception = new ValidationException("Test", cause);

        Assert.assertEquals("Test", exception.getMessage());
        Assert.assertEquals(cause, exception.getCause());
    }

    @Test(expected = ValidationException.class)
    public void testException() throws ValidationException {

        throw new ValidationException("Test");
    }
}
