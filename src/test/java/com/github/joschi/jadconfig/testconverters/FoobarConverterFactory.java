package com.github.joschi.jadconfig.testconverters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ConverterFactory;

public class FoobarConverterFactory implements ConverterFactory {
    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Converter<?>> getConverter(Class classType) {
        if (classType == String.class) {
            return FoobarConverter.class;
        }

        return null;
    }
}