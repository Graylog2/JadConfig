package com.github.joschi.jadconfig;

/**
 * Interface for parameter validators
 * <p/>
 * The validator will be called before any {@link Converter} has been run on the configuration parameter
 *
 * @author jschalanda
 */
public interface Validator {

    /**
     * Validates the value {@literal value} the of provided configuration parameter {@literal name}
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration validator
     * @throws ValidationException If the value {@literal value} configuration parameter {@literal name} can't be validated
     */
    void validate(String name, String value) throws ValidationException;
}
