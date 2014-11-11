package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

/**
 * {@link Validator} class doing nothing being used as default validator
 *
 * @author jschalanda
 */
public class NoValidator implements Validator<Object> {

    /**
     * Does nothing and is being used as default {@link Validator#validate(String, Object)} method
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws ValidationException Never
     */
    @Override
    public void validate(String name, Object value) throws ValidationException {
    }
}
