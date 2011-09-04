package com.github.joschi.jadconfig.testconverters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ConverterFactory;

/**
 * @author jschalanda
 */
public class FoobarConverterFactory implements ConverterFactory {

    @Override
    public Class<? extends Converter<?>> getConverter(Class classType) {
        if(classType == String.class) {
            return FoobarConverter.class;
        }

        return null;
    }
}