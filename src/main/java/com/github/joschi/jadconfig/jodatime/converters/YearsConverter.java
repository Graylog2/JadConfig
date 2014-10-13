package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Years;

/**
 * Converter for type {@link org.joda.time.Years}
 *
 * @author jschalanda
 */
public class YearsConverter implements Converter<Years> {

    /**
     * Returns a {@link org.joda.time.Years} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.Years} instance representing the configuration parameter's value
     */
    @Override
    public Years convertFrom(String value) {
        try {
            final String trimmed = value.trim();
            if (trimmed.startsWith("P")) {
                return Years.parseYears(trimmed);
            } else {
                final int years = Integer.parseInt(trimmed);
                return Years.years(years);
            }
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Years.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link org.joda.time.Years} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Years value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return String.valueOf(value.getYears());
    }
}
