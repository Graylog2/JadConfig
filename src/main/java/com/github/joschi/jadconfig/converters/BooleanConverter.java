package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

/**
 * Converter for type {@link Boolean}
 *
 * @author jschalanda
 */
public class BooleanConverter implements Converter<Boolean> {

    /**
     * Returns a {@link Boolean} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Boolean} instance representing the configuration parameter's value
     */
    @Override
    public Boolean convertFrom(String value) {

        String trimmedValue = Strings.trim(value);

        if ("false".equalsIgnoreCase(trimmedValue) || "true".equalsIgnoreCase(trimmedValue)) {
            return Boolean.valueOf(trimmedValue);
        } else {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Boolean.");
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Boolean} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Boolean value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
