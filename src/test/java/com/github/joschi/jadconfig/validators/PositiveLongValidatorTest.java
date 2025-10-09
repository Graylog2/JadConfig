package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.validators.PositiveLongValidator}
 *
 * @author jschalanda
 */
public class PositiveLongValidatorTest {

    private PositiveLongValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PositiveLongValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", 0L);
        validator.validate("Test", 1L);
        validator.validate("Test", Long.MAX_VALUE);
    }

    @Test
    public void testValidateNegative() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", -1L)
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
