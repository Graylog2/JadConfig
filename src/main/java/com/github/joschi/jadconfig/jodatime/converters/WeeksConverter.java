package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Weeks;

/**
 * Converter for type {@link org.joda.time.Weeks}
 *
 * @author jschalanda
 */
public class WeeksConverter implements Converter<Weeks> {

    /**
     * Returns a {@link org.joda.time.Weeks} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.Weeks} instance representing the configuration parameter's value
     */
    @Override
    public Weeks convertFrom(String value) {
        try {
            final String trimmed = value.trim();
            if (trimmed.startsWith("P")) {
                return Weeks.parseWeeks(trimmed);
            } else {
                final int weeks = Integer.parseInt(trimmed);
                return Weeks.weeks(weeks);
            }
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Weeks.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link org.joda.time.Weeks} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Weeks value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return String.valueOf(value.getWeeks());
    }
}
