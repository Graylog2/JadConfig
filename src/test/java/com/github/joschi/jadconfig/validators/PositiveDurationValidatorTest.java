package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
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
        validator.validate("Test", "0s");
        validator.validate("Test", "1ns");
        validator.validate("Test", "2147483647m");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", "-1s");
    }

    @Test(expected = ValidationException.class)
    public void testValidateInvalidDuration() throws ValidationException {
        validator.validate("Test", "Not a valid Duration");
    }

    @Test(expected = ValidationException.class)
    public void testValidateEmpty() throws ValidationException {
        validator.validate("Test", "");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
