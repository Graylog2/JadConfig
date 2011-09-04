package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: joschi
 * Date: 28.08.11
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class ByteConverter implements Converter<Byte> {

    @Override
    public Byte convert(String value) {

        Byte result;

        try {
            result = Byte.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Byte.", ex);
        }

        return result;
    }
}
