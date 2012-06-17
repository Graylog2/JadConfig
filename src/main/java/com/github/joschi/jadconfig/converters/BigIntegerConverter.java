package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.math.BigInteger;

/**
 * Converter for type {@link java.math.BigInteger}
 *
 * @author jschalanda
 */
public class BigIntegerConverter implements Converter<BigInteger> {

    /**
     * Returns a {@link java.math.BigInteger} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link java.math.BigInteger} instance representing the configuration parameter's value
     */
    @Override
    public BigInteger convertFrom(String value) {

        BigInteger result;

        try {
            result = new BigInteger(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to BigInteger.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link java.math.BigInteger} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(BigInteger value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
