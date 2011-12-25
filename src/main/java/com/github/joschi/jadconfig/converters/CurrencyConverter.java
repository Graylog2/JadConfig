package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

import java.util.Currency;

/**
 * Converter for type {@link Currency}
 *
 * @author jschalanda
 */
public class CurrencyConverter implements Converter<Currency> {

    /**
     * Returns a {@link Currency} instance representing the specified ISO 4217 code {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Currency} instance representing the configuration parameter's value
     * @see java.util.Currency#getInstance(String)
     */
    @Override
    public Currency convertFrom(String value) {

        Currency result;

        try {
            result = Currency.getInstance(Strings.trim(value));
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Currency.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the ISO 4217 code of the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Currency} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     * @see java.util.Currency#getCurrencyCode()
     */
    @Override
    public String convertTo(Currency value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getCurrencyCode();
    }
}
