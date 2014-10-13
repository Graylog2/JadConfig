package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.util.SizeUnit;

import java.util.Locale;

/**
 * Converter for type {@link SizeUnit}
 *
 * @author jschalanda
 */
public class SizeUnitConverter implements Converter<SizeUnit> {

    /**
     * Returns a {@link SizeUnit} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link SizeUnit} instance representing the configuration parameter's value
     */
    @Override
    public SizeUnit convertFrom(String value) {
        try {
            return SizeUnit.valueOf(value.toUpperCase(Locale.ENGLISH));
        } catch (Exception ex) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to SizeUnit.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link SizeUnit} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(SizeUnit value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
