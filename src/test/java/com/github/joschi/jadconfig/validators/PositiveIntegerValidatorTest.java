package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link PositiveIntegerValidator}
 *
 * @author jschalanda
 */
public class PositiveIntegerValidatorTest {

    private PositiveIntegerValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PositiveIntegerValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", 0);
        validator.validate("Test", 1);
        validator.validate("Test", Integer.MAX_VALUE);
    }

    @Test
    public void testValidateNegative() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", -1)
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
