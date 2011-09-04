package com.github.joschi.jadconfig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ParameterException}
 *
 * @author jschalanda
 */
public class ParameterExceptionTest {

    ParameterException exception;

    @Test
    public void testConstructor() {

        exception = new ParameterException();

        Assert.assertNull(exception.getMessage());
        Assert.assertNull(exception.getCause());

        exception = new ParameterException("Test");

        Assert.assertEquals("Test", exception.getMessage());
        Assert.assertNull(exception.getCause());

        Exception cause = new Exception("Cause");
        exception = new ParameterException("Test", cause);

        Assert.assertEquals("Test", exception.getMessage());
        Assert.assertEquals(cause, exception.getCause());
    }

    @Test(expected = ParameterException.class)
    public void testRuntimeException() {

        throw new ParameterException("Test");
    }
}
