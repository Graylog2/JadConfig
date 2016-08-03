package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

import java.net.URI;

/**
 * {@link Validator} class which checks if the named parameter is an absolute {@link URI}.
 *
 * @author jschalanda
 */
public class URIAbsoluteValidator implements Validator<URI> {

    /**
     * Validates if the value {@literal value} the of provided configuration parameter {@literal name} is an absolute
     * {@link URI}.
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws ValidationException If the value {@literal value} configuration parameter {@literal name} can't be parsed
     *                             as a {@link URI} or is not absolute.
     */
    @Override
    public void validate(final String name, final URI value) throws ValidationException {
        if (value != null && !value.isAbsolute()) {
            throw new ValidationException("Parameter " + name + " should be an absolute URI (found " + value + ")");
        }
    }
}
