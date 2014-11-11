package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.validators.PositiveLongValidator}
 *
 * @author jschalanda
 */
public class PositiveLongValidatorTest {

    private PositiveLongValidator validator;

    @Before
    public void setUp() {
        validator = new PositiveLongValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", 0L);
        validator.validate("Test", 1L);
        validator.validate("Test", Long.MAX_VALUE);
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", -1L);
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
