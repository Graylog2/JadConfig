package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Byte}
 *
 * @author jschalanda
 */
public class ByteConverter implements Converter<Byte> {

    @Override
    public Byte convertFrom(String value) {

        Byte result;

        try {
            result = Byte.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Byte.", ex);
        }

        return result;
    }

    @Override
    public String convertTo(Byte value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
