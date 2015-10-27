package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link StringLowercaseValidator}
 *
 * @author jschalanda
 */
public class StringLowercaseValidatorTest {

    private StringLowercaseValidator validator;

    @Before
    public void setUp() {
        validator = new StringLowercaseValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "");
        validator.validate("Test", "test");
        validator.validate("Test", "1234");
        validator.validate("Test", "test1234");
    }

    @Test(expected = ValidationException.class)
    public void testValidateUppercaseString() throws ValidationException {
        validator.validate("Test", "TEST");
    }

    @Test(expected = ValidationException.class)
    public void testValidateMixedCaseString() throws ValidationException {
        validator.validate("Test", "Test1234");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
