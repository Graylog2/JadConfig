package com.github.joschi.jadconfig;

/**
 * @author jschalanda
 */
public interface ConverterFactory {

    <T> Class<? extends Converter<T>> getConverter(Class<T> classType);
}