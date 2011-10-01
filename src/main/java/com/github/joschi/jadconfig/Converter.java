package com.github.joschi.jadconfig;

/**
 * Interface for type converters.
 *
 * A type converter will convert a configuration parameter's value (provided as {@link String}) to another type
 * and vice versa.
 *
 * @author jschalanda
 */
public interface Converter<T> {

    /**
     * Returns a typed instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A typed instance representing the configuration parameter's value
     */
    T convertFrom(String value);

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's typed value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    String convertTo(T value);
}
