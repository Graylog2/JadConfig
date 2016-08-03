package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * Unit tests for {@link URIAbsoluteValidator}
 *
 * @author jschalanda
 */
public class URIAbsoluteValidatorTest {

    private URIAbsoluteValidator validator;

    @Before
    public void setUp() {
        validator = new URIAbsoluteValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        validator.validate("Test", URI.create("http://www.example.com/"));
        validator.validate("Test", URI.create("ftp://example.com/test"));
        validator.validate("Test", URI.create("test://example.com/test?foo=bar#foobaz"));
    }

    @Test(expected = ValidationException.class)
    public void testValidateRelativeURI() throws ValidationException {
        validator.validate("Test", URI.create("/nope"));
    }

    @Test(expected = ValidationException.class)
    public void testValidateEmptyURI() throws ValidationException {
        validator.validate("Test", URI.create(""));
    }

    @Test
    public void testValidateNull() throws ValidationException {
        validator.validate("Test", null);
    }
}
