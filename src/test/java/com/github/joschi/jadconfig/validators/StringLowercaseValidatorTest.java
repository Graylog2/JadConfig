package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringLowercaseValidator}
 *
 * @author jschalanda
 */
public class StringLowercaseValidatorTest {

    private StringLowercaseValidator validator;

    @BeforeEach
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

    @Test
    public void testValidateUppercaseString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", "TEST")
        );
    }

    @Test
    public void testValidateMixedCaseString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", "Test1234")
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
