package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Converter for type {@link org.joda.time.format.DateTimeFormatter}
 *
 * @author jschalanda
 * @see org.joda.time.format.DateTimeFormat
 * @see org.joda.time.format.DateTimeFormatter
 */
public class DateTimeFormatterConverter implements Converter<DateTimeFormatter> {

    /**
     * Returns a {@link org.joda.time.format.DateTimeFormatter} instance representing the specified pattern.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.format.DateTimeFormatter} instance representing the configuration parameter's value
     * @see org.joda.time.format.DateTimeFormat#forPattern(String)
     */
    @Override
    public DateTimeFormatter convertFrom(String value) {
        try {
            return DateTimeFormat.forPattern(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to DateTimeFormatter.", ex);
        }
    }

    /**
     * Returns an empty {@link String} since getting the original pattern from a
     * {@link org.joda.time.format.DateTimeFormatter} isn't supported by Joda-Time.
     *
     * @param value The configuration parameter's {@link org.joda.time.format.DateTimeFormatter} value
     * @return An empty {@link String}
     */
    @Override
    public String convertTo(DateTimeFormatter value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return "";
    }
}
