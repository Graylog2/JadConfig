package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link StringUppercaseValidator}
 *
 * @author jschalanda
 */
public class StringUppercaseValidatorTest {

    private StringUppercaseValidator validator;

    @Before
    public void setUp() {
        validator = new StringUppercaseValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "");
        validator.validate("Test", "TEST");
        validator.validate("Test", "1234");
        validator.validate("Test", "TEST1234");
    }

    @Test(expected = ValidationException.class)
    public void testValidateLowercaseString() throws ValidationException {
        validator.validate("Test", "Test");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
