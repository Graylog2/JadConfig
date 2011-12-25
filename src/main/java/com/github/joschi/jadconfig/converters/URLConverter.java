package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

import java.net.URL;

/**
 * Converter for type {@link URL}
 *
 * @author jschalanda
 */
public class URLConverter implements Converter<URL> {

    /**
     * Returns a {@link java.net.URL} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} representation
     * @return A {@link java.net.URL} instance representing the configuration parameter's value
     */
    @Override
    public URL convertFrom(String value) {

        URL result;

        try {
            result = new URL(Strings.trim(value));
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to URL.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link java.net.URL} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(URL value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
