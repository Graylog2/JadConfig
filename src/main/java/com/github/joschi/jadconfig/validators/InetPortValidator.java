package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

/**
 * {@link com.github.joschi.jadconfig.Validator} class which checks if the named parameter is a valid UDP/TCP port (0-65535).
 *
 * @author jschalanda
 */
public class InetPortValidator implements Validator {

    /**
     * Validates if the value {@literal value} the of provided configuration parameter {@literal name} is a valid
     * UDP/TCP port (0-65535)
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws com.github.joschi.jadconfig.ValidationException
     *          If the value {@literal value} configuration parameter {@literal name} can't be parsed
     *          as an {@literal int} or is not in the range of 0-65535.
     */
    @Override
    public void validate(String name, String value) throws ValidationException {

        int n;
        String trimmedValue = value == null ? null : value.trim();

        try {
            n = Integer.parseInt(trimmedValue);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Parameter " + name + " should be a positive number (found " + value + ")", ex);
        }

        if (n < 0 || n > 65535) {
            throw new ValidationException("Parameter " + name + " should be within range 0-65535 (found " + value + ")");
        }
    }
}
