package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.nio.charset.Charset;

/**
 * Converter for type {@link Charset}
 *
 * @author jschalanda
 */
public class CharsetConverter implements Converter<Charset> {

    /**
     * Returns a {@link Charset} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Charset} instance representing the configuration parameter's value
     * @see Charset#forName(String)
     */
    @Override
    public Charset convertFrom(String value) {

        Charset result;

        try {
            result = Charset.forName(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Charset.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Charset} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     * @see Charset#name()
     */
    @Override
    public String convertTo(Charset value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.name();
    }
}
