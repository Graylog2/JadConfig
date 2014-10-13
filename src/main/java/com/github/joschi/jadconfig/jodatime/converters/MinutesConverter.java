package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Minutes;

/**
 * Converter for type {@link org.joda.time.Minutes}
 *
 * @author jschalanda
 */
public class MinutesConverter implements Converter<Minutes> {

    /**
     * Returns a {@link org.joda.time.Minutes} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.Minutes} instance representing the configuration parameter's value
     */
    @Override
    public Minutes convertFrom(String value) {
        try {
            final String trimmed = value.trim();
            if (trimmed.startsWith("P")) {
                return Minutes.parseMinutes(trimmed);
            } else {
                final int minutes = Integer.parseInt(trimmed);
                return Minutes.minutes(minutes);
            }
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Minutes.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link org.joda.time.Minutes} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Minutes value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return String.valueOf(value.getMinutes());
    }
}
