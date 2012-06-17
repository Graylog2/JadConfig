package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.util.Locale;

/**
 * Converter for type {@link Locale}
 *
 * @author jschalanda
 */
public class LocaleConverter implements Converter<Locale> {

    /**
     * Returns a {@link Locale} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Locale} instance representing the configuration parameter's value
     */
    @Override
    public Locale convertFrom(String value) {

        Locale result = getLocale(value);

        if (null == result) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Locale");
        }

        return result;
    }

    private Locale getLocale(String languageTag) {

        if (null == languageTag) {
            return null;
        }

        String[] components = languageTag.split("_", 3);

        switch (components.length) {
            case 1:
                return new Locale(components[0]);
            case 2:
                return new Locale(components[0], components[1]);
            case 3:
                return new Locale(components[0], components[1], components[2]);
            default:
                return null;
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Locale} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Locale value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
