package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Float}
 *
 * @author jschalanda
 */
public class FloatConverter implements Converter<Float> {

    @Override
    public Float convert(String value) {

        Float result;

        try {
            result = Float.valueOf(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Float.", ex);
        }

        return result;
    }
}
