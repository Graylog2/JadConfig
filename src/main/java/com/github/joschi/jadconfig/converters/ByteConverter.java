package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.Strings;

/**
 * Converter for type {@link Byte}
 *
 * @author jschalanda
 */
public class ByteConverter implements Converter<Byte> {

    /**
     * Returns a {@link Byte} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Byte} instance representing the configuration parameter's value
     */
    @Override
    public Byte convertFrom(String value) {

        Byte result;

        try {
            result = Byte.valueOf(Strings.trim(value));
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Byte.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Byte} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Byte value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
