package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

/**
 * {@link Validator} class which checks if the named parameter is a positive integer.
 *
 * @author jschalanda
 */
public class PositiveIntegerValidator implements Validator {

    /**
     * Validates if the value {@literal value} the of provided configuration parameter {@literal name} is a positive
     * integer
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws ValidationException If the value {@literal value} configuration parameter {@literal name} can't be parsed
     *                             as an {@literal int} or is negative.
     */
    @Override
    public void validate(String name, String value) throws ValidationException {

        int n;

        try {
            n = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Parameter " + name + " should be a positive number (found " + value + ")");
        }

        if (n < 0) {
            throw new ValidationException("Parameter " + name + " should be positive (found " + value + ")");
        }
    }
}
