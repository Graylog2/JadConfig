package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.util.Duration;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link PositiveDurationValidator}
 *
 * @author jschalanda
 */
public class PositiveDurationValidatorTest {
    private PositiveDurationValidator validator;

    @Before
    public void setUp() {
        validator = new PositiveDurationValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", Duration.nanoseconds(0l));
        validator.validate("Test", Duration.nanoseconds(1l));
        validator.validate("Test", Duration.nanoseconds(Long.MAX_VALUE));
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", Duration.nanoseconds(-1l));
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
