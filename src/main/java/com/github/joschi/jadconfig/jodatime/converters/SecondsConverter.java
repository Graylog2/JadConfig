package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Seconds;

/**
 * Converter for type {@link org.joda.time.Seconds}
 *
 * @author jschalanda
 */
public class SecondsConverter implements Converter<Seconds> {

    /**
     * Returns a {@link Seconds} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Seconds} instance representing the configuration parameter's value
     */
    @Override
    public Seconds convertFrom(String value) {
        try {
            final String trimmed = value.trim();
            if (trimmed.startsWith("P")) {
                return Seconds.parseSeconds(trimmed);
            } else {
                final int seconds = Integer.parseInt(trimmed);
                return Seconds.seconds(seconds);
            }
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Seconds.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Seconds} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Seconds value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return String.valueOf(value.getSeconds());
    }
}
