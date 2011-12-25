package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.util.regex.Pattern;

/**
 * Converter for type {@link Pattern}
 *
 * @author jschalanda
 */
public class PatternConverter implements Converter<Pattern> {

    /**
     * Returns a {@link Pattern} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Pattern} instance representing the configuration parameter's value
     */
    @Override
    public Pattern convertFrom(String value) {

        Pattern result;

        try {
            result = Pattern.compile(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Pattern.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Pattern} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Pattern value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
