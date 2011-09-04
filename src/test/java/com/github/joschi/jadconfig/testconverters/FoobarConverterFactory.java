package com.github.joschi.jadconfig.testconverters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ConverterFactory;

/**
 * Created by IntelliJ IDEA.
 * User: joschi
 * Date: 04.09.11
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
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