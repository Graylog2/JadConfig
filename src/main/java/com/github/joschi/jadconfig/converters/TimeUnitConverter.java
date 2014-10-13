package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Converter for type {@link TimeUnit}
 *
 * @author jschalanda
 */
public class TimeUnitConverter implements Converter<TimeUnit> {

    /**
     * Returns a {@link TimeUnit} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link TimeUnit} instance representing the configuration parameter's value
     */
    @Override
    public TimeUnit convertFrom(String value) {
        try {
            return TimeUnit.valueOf(value.toUpperCase(Locale.ENGLISH));
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to TimeUnit.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link TimeUnit} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(TimeUnit value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
