package de.schalanda.jadconfig;

/**
 * @author jschalanda
 */
public interface ConverterFactory {

    <T> Class<? extends Converter<T>> getConverter(Class<T> classType);
}