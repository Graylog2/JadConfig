package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.util.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link PositiveDurationValidator}
 *
 * @author jschalanda
 */
public class PositiveDurationValidatorTest {
    private PositiveDurationValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PositiveDurationValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", Duration.nanoseconds(0L));
        validator.validate("Test", Duration.nanoseconds(1L));
        validator.validate("Test", Duration.nanoseconds(Long.MAX_VALUE));
    }

    @Test
    public void testValidateNegative() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", Duration.nanoseconds(-1L))
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
