package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link StringNotBlankValidator}
 *
 * @author jschalanda
 */
public class StringNotBlankValidatorTest {

    private StringNotBlankValidator validator;

    @Before
    public void setUp() {
        validator = new StringNotBlankValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "Test");
    }

    @Test(expected = ValidationException.class)
    public void testValidateEmptyString() throws ValidationException {
        validator.validate("Test", "");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNewLineString() throws ValidationException {
        validator.validate("Test", "\n");
    }

    @Test(expected = ValidationException.class)
    public void testValidateBlankString() throws ValidationException {
        validator.validate("Test", " ");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
