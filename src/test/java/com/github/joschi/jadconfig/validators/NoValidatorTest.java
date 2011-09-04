package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link NoValidator}
 *
 * @author jschalanda
 */
public class NoValidatorTest {

    NoValidator validator;

    @Before
    public void setUp() {

        validator = new NoValidator();
    }

    @Test
    public void testValidate() throws ValidationException {

        validator.validate("Test", "test");
    }
}
