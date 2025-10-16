package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringUppercaseValidator}
 *
 * @author jschalanda
 */
public class StringUppercaseValidatorTest {

    private StringUppercaseValidator validator;

    @BeforeEach
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

    @Test
    public void testValidateLowercaseString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", "Test")
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
