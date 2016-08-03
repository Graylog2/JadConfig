package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

/**
 * {@link com.github.joschi.jadconfig.Validator} class which checks if the named parameter is a positive long.
 *
 * @author jschalanda
 */
public class PositiveLongValidator implements Validator<Long> {

    /**
     * Validates if the value {@literal value} the of provided configuration parameter {@literal name} is a positive
     * long
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws com.github.joschi.jadconfig.ValidationException
     *          If the value {@literal value} configuration parameter {@literal name} can't be parsed
     *          as an {@literal long} or is negative.
     */
    @Override
    public void validate(String name, Long value) throws ValidationException {
        if (value != null && value < 0L) {
            throw new ValidationException("Parameter " + name + " should be positive (found " + value + ")");
        }
    }
}
