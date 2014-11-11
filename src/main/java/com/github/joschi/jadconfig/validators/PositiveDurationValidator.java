package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;
import com.github.joschi.jadconfig.util.Duration;

/**
 * {@link Validator} class which checks if the named parameter is a positive {@link Duration}.
 *
 * @author jschalanda
 */
public class PositiveDurationValidator implements Validator {

    /**
     * Validates if the value {@literal value} the of provided configuration parameter {@literal name} is a positive
     * {@link Duration}
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws ValidationException If the value {@literal value} configuration parameter {@literal name} can't be parsed
     *                             as an {@link Duration} or is negative.
     */
    @Override
    public void validate(final String name, final String value) throws ValidationException {
        Duration duration;

        try {
            duration = Duration.parse(value);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Parameter " + name + " should be a positive duration (found " + value + ")", ex);
        } catch (NullPointerException ex) {
            throw new ValidationException("Parameter " + name + " should be a positive duration (found " + value + ")", ex);
        }

        if (duration.getQuantity() < 0l) {
            throw new ValidationException("Parameter " + name + " should be positive (found " + value + ")");
        }
    }
}
