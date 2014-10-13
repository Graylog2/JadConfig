package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Interval;

/**
 * Converter for type {@link org.joda.time.Interval}
 *
 * @author jschalanda
 */
public class IntervalConverter implements Converter<Interval> {

    /**
     * Returns a {@link org.joda.time.Interval} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link org.joda.time.Interval} instance representing the configuration parameter's value
     * @see Interval#parse(String)
     */
    @Override
    public Interval convertFrom(String value) {
        if(null == value) {
            throw new ParameterException("Couldn't convert \"null\" to Interval.");
        }

        try {
            return Interval.parse(value);
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Interval.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link org.joda.time.Interval} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Interval value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
