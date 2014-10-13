package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Hours;

/**
 * Converter for type {@link org.joda.time.Hours}
 *
 * @author jschalanda
 */
public class HoursConverter implements Converter<Hours> {

    /**
     * Returns a {@link org.joda.time.Hours} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.Hours} instance representing the configuration parameter's value
     */
    @Override
    public Hours convertFrom(String value) {
        try {
            final String trimmed = value.trim();
            if (trimmed.startsWith("P")) {
                return Hours.parseHours(trimmed);
            } else {
                final int hours = Integer.parseInt(trimmed);
                return Hours.hours(hours);
            }
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Hours.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link org.joda.time.Hours} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Hours value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return String.valueOf(value.getHours());
    }
}
