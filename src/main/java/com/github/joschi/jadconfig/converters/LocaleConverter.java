package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

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
     * @see Locale#forLanguageTag(String)
     */
    @Override
    public Locale convertFrom(String value) {

        Locale result;

        try {
            result = Locale.forLanguageTag(Strings.trim(value));
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Locale.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Locale} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     * @see Locale#toLanguageTag()
     */
    @Override
    public String convertTo(Locale value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toLanguageTag();
    }
}
