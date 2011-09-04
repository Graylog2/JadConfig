package com.github.joschi.jadconfig;

/**
 * @author jschalanda
 */
public interface Converter<T> {

    T convert(String value);
}
