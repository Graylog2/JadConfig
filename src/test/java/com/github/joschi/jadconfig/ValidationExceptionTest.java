package com.github.joschi.jadconfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link ValidationException}
 *
 * @author jschalanda
 */
public class ValidationExceptionTest {

    private ValidationException exception;

    @Test
    public void testConstructor() {

        exception = new ValidationException();

        Assertions.assertNull(exception.getMessage());
        Assertions.assertNull(exception.getCause());

        exception = new ValidationException("Test");

        Assertions.assertEquals("Test", exception.getMessage());
        Assertions.assertNull(exception.getCause());

        Exception cause = new Exception("Cause");
        exception = new ValidationException("Test", cause);

        Assertions.assertEquals("Test", exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
    }

    @Test
    public void testException() {
        assertThrows(ValidationException.class,
                () -> {
                    throw new ValidationException("Test");
                });
    }
}
