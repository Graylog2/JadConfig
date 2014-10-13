package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Months;

/**
 * Converter for type {@link org.joda.time.Months}
 *
 * @author jschalanda
 */
public class MonthsConverter implements Converter<Months> {

    /**
     * Returns a {@link org.joda.time.Months} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.Months} instance representing the configuration parameter's value
     */
    @Override
    public Months convertFrom(String value) {
        try {
            final String trimmed = value.trim();
            if (trimmed.startsWith("P")) {
                return Months.parseMonths(value);
            } else {
                final int months = Integer.parseInt(value);
                return Months.months(months);
            }
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Months.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link org.joda.time.Months} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Months value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return String.valueOf(value.getMonths());
    }
}
