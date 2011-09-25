package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.net.URI;

/**
 * Converter for type {@link URI}
 *
 * @author jschalanda
 */
public class URIConverter implements Converter<URI> {

    /**
     * Returns a {@link URI} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} representation
     * @return A {@link URI} instance representing the configuration parameter's value
     */
    @Override
    public URI convertFrom(String value) {

        URI result;

        try {
            result = new URI(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to URI.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link URI} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(URI value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
