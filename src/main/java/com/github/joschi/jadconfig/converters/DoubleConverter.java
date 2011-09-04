package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Double}
 *
 * @author jschalanda
 */
public class DoubleConverter implements Converter<Double> {

    @Override
    public Double convert(String value) {

        Double result;

        try {
            result = Double.valueOf(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Double.", ex);
        }

        return result;
    }
}
