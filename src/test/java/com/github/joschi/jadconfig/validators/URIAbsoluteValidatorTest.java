package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link URIAbsoluteValidator}
 *
 * @author jschalanda
 */
public class URIAbsoluteValidatorTest {

    private URIAbsoluteValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new URIAbsoluteValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", URI.create("http://www.example.com/"));
        validator.validate("Test", URI.create("ftp://example.com/test"));
        validator.validate("Test", URI.create("test://example.com/test?foo=bar#foobaz"));
    }

    @Test
    public void testValidateRelativeURI() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", URI.create("/nope"))
        );
    }

    @Test
    public void testValidateEmptyURI() {
        assertThrows(ValidationException.class,
                () -> validator.validate("Test", URI.create(""))
        );
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
