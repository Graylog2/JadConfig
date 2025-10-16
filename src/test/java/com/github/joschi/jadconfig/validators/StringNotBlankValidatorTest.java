package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringNotBlankValidator}
 *
 * @author jschalanda
 */
public class StringNotBlankValidatorTest {

    private StringNotBlankValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new StringNotBlankValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "Test");
    }

    @Test
    public void testValidateEmptyString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", "")
        );
    }

    @Test
    public void testValidateNewLineString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", "\n")
        );
    }

    @Test
    public void testValidateBlankString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", " ")
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
