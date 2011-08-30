package de.schalanda.jadconfig.validators;

import de.schalanda.jadconfig.ValidationException;
import de.schalanda.jadconfig.Validator;

/**
 * {@link Validator} class doing nothing being used as default validator
 *
 * @author jschalanda
 */
public class NoValidator implements Validator {

    @Override
    public void validate(String name, String value) throws ValidationException {
    }
}
