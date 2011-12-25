package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

import java.util.TimeZone;

/**
 * Converter for type {@link TimeZone}
 *
 * @author jschalanda
 */
public class TimeZoneConverter implements Converter<TimeZone> {

    /**
     * Returns a {@link TimeZone} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link TimeZone} instance representing the configuration parameter's value
     * @see TimeZone#getTimeZone(String)
     */
    @Override
    public TimeZone convertFrom(String value) {

        TimeZone result;

        try {
            result = TimeZone.getTimeZone(Strings.trim(value));
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to TimeZone.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link TimeZone} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     * @see TimeZone#getID()
     */
    @Override
    public String convertTo(TimeZone value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getID();
    }
}
