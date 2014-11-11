package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.util.Size;
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
        validator.validate("Test", Size.bytes(0l));
        validator.validate("Test", Size.bytes(1l));
        validator.validate("Test", Size.bytes(Long.MAX_VALUE));
    }

    @Test(expected = ValidationException.class)
    public void testValidateNegative() throws ValidationException {
        validator.validate("Test", Size.bytes(-1l));
    }

    @Test(expected = ValidationException.class)
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
