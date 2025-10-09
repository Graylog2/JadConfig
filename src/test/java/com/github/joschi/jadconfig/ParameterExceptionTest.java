package com.github.joschi.jadconfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link ParameterException}
 *
 * @author jschalanda
 */
public class ParameterExceptionTest {

    private ParameterException exception;

    @Test
    public void testConstructor() {

        exception = new ParameterException();

        Assertions.assertNull(exception.getMessage());
        Assertions.assertNull(exception.getCause());

        exception = new ParameterException("Test");

        Assertions.assertEquals("Test", exception.getMessage());
        Assertions.assertNull(exception.getCause());

        Exception cause = new Exception("Cause");
        exception = new ParameterException("Test", cause);

        Assertions.assertEquals("Test", exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
    }

    @Test
    public void testRuntimeException() {
        assertThrows(ParameterException.class,
                () -> {
                    throw new ParameterException("Test");
        });
    }
}
