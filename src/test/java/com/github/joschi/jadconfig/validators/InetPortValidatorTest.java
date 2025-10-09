package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link com.github.joschi.jadconfig.validators.InetPortValidator}
 *
 * @author jschalanda
 */
public class InetPortValidatorTest {

    private InetPortValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new InetPortValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", 0);
        validator.validate("Test", 1);
        validator.validate("Test", 65535);
    }

    @Test
    public void testValidateNegative() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", -1)
        );
    }

    @Test
    public void testValidateTooBig() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", 65536)
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
