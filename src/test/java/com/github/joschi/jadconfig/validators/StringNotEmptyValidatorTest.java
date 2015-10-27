package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link StringNotEmptyValidator}
 *
 * @author jschalanda
 */
public class StringNotEmptyValidatorTest {

    private StringNotEmptyValidator validator;

    @Before
    public void setUp() {
        validator = new StringNotEmptyValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "Test");
        validator.validate("Test", " ");
        validator.validate("Test", "\n");
    }

    @Test(expected = ValidationException.class)
    public void testValidateEmptyString() throws ValidationException {
        validator.validate("Test", "");
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
