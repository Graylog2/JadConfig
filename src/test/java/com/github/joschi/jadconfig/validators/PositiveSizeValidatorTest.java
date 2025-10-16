package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.util.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link PositiveSizeValidator}
 *
 * @author jschalanda
 */
public class PositiveSizeValidatorTest {
    private PositiveSizeValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PositiveSizeValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", Size.bytes(0l));
        validator.validate("Test", Size.bytes(1l));
        validator.validate("Test", Size.bytes(Long.MAX_VALUE));
    }

    @Test
    public void testValidateNegative() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", Size.bytes(-1l))
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
