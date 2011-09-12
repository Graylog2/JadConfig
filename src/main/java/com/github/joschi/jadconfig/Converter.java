package com.github.joschi.jadconfig;

/**
 * @author jschalanda
 */
public interface Converter<T> {

    T convertFrom(String value);

    String convertTo(T value);
}
