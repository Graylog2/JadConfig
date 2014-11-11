package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link PositiveIntegerValidator}
 *
 * @author jschalanda
 */
public class PositiveIntegerValidatorTest {

    private PositiveIntegerValidator validator;

    @Before
    public void setUp() {
        validator = new PositiveIntegerValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", 0);
        validator.validate("Test", 1);
        validator.validate("Test", Integer.MAX_VALUE);
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", -1);
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
