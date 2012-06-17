package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.math.BigDecimal;

/**
 * Converter for type {@link BigDecimal}
 *
 * @author jschalanda
 */
public class BigDecimalConverter implements Converter<BigDecimal> {

    /**
     * Returns a {@link BigDecimal} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link BigDecimal} instance representing the configuration parameter's value
     */
    @Override
    public BigDecimal convertFrom(String value) {

        BigDecimal result;

        try {
            result = new BigDecimal(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to BigDecimal.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link BigDecimal} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(BigDecimal value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
