package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.text.DecimalFormat;

/**
 * Converter for type {@link java.text.DecimalFormat}
 *
 * @author jschalanda
 */
public class DecimalFormatConverter implements Converter<DecimalFormat> {

    /**
     * Returns a {@link java.text.DecimalFormat} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link java.text.DecimalFormat} instance representing the configuration parameter's value
     */
    @Override
    public DecimalFormat convertFrom(String value) {

        DecimalFormat result;

        try {
            result = new DecimalFormat(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to DecimalFormat.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link java.text.DecimalFormat} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(DecimalFormat value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toPattern();
    }
}
