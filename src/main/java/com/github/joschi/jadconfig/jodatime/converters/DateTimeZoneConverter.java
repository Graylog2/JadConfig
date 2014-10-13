package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.DateTimeZone;

/**
 * Converter for type {@link org.joda.time.DateTimeZone}
 *
 * @author jschalanda
 */
public class DateTimeZoneConverter implements Converter<DateTimeZone> {

    /**
     * Returns a {@link org.joda.time.DateTimeZone} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.DateTimeZone} instance representing the configuration parameter's value
     * @see org.joda.time.DateTimeZone#forID(String)
     */
    @Override
    public DateTimeZone convertFrom(String value) {
        try {
            return DateTimeZone.forID(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to DateTimeZone.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link java.util.TimeZone} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     * @see java.util.TimeZone#getID()
     */
    @Override
    public String convertTo(DateTimeZone value) {
        if (value == null) {
            return DateTimeZone.getDefault().getID();
        }

        return value.getID();
    }
}
