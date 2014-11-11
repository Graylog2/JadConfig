package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link PositiveSizeValidator}
 *
 * @author jschalanda
 */
public class PositiveSizeValidatorTest {
    private PositiveSizeValidator validator;

    @Before
    public void setUp() {
        validator = new PositiveSizeValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "0mb");
        validator.validate("Test", "1gb");
        validator.validate("Test", "2147483647kb");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", "-1mb");
    }

    @Test(expected = ValidationException.class)
    public void testValidateInvalidSize() throws ValidationException {
        validator.validate("Test", "Not a valid Size");
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
