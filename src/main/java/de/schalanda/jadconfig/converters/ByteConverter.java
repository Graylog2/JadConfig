package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.Converter;
import de.schalanda.jadconfig.ParameterException;

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
