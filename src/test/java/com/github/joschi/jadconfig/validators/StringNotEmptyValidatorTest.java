package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringNotEmptyValidator}
 *
 * @author jschalanda
 */
public class StringNotEmptyValidatorTest {

    private StringNotEmptyValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new StringNotEmptyValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", "Test");
        validator.validate("Test", " ");
        validator.validate("Test", "\n");
    }

    @Test
    public void testValidateEmptyString() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", "")
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
