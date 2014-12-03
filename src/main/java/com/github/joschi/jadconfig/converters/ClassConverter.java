package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.lang.Class;

/**
 * Class to convert from a given FQCN (fully qualified class name) to a {@link java.lang.Class} object
 */
public class ClassConverter implements Converter<Class> {
    /**
     * Returns a {@link java.lang.Class} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link java.lang.Class} instance representing the configuration parameter's value
     */
    @Override
    public Class convertFrom(String value) {
        try {
            return Class.forName(value);
        } catch (Exception e) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Class object.");
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link java.lang.Class} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Class value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getCanonicalName();
    }
}
