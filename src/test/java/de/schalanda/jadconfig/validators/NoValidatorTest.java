package de.schalanda.jadconfig.validators;

import de.schalanda.jadconfig.ValidationException;
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
