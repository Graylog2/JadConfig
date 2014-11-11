package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.validators.InetPortValidator}
 *
 * @author jschalanda
 */
public class InetPortValidatorTest {

    private InetPortValidator validator;

    @Before
    public void setUp() {
        validator = new InetPortValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", 0);
        validator.validate("Test", 1);
        validator.validate("Test", 65535);
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", -1);
    }

    @Test(expected = ValidationException.class)
    public void testValidateTooBig() throws ValidationException {
        validator.validate("Test", 65536);
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
