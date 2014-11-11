package com.github.joschi.jadconfig;

/**
 * Interface for parameter validators
 * <p>
 * The validator will be called before any {@link Converter} has been run on the configuration parameter
 * </p>
 *
 * @param <T> The type for which this Validator works.
 * @author jschalanda
 */
public interface Validator<T> {
    /**
     * Validates the value {@literal value} the of provided configuration parameter {@literal name}
     *
     * @param name  The name of the configuration parameter
     * @param value The value of the configuration parameter. Might also be {@code null}.
     * @throws ValidationException If the configuration parameter {@literal name} couldn't be validated
     */
    void validate(String name, T value) throws ValidationException;
}
