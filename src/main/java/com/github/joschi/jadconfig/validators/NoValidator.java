package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

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
