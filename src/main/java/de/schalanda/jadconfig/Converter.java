package de.schalanda.jadconfig;

/**
 * @author jschalanda
 */
public interface Converter<T> {

    T convert(String value);
}
