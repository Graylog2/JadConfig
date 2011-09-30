package com.github.joschi.jadconfig;

/**
 * Interface for converter factories
 *
 * A converter factory can be used to provider {@link Converter}s for several types instead of using
 * the {@link com.github.joschi.jadconfig.Parameter#converter()} annotation parameter for each class field.
 *
 * @author jschalanda
 */
public interface ConverterFactory {

    /**
     * Finds and returns a {@link Converter} for the provided class type.
     *
     * @param classType The class type for which to find and return a {@link Converter}
     * @param <T> Type of the {@link Converter} to get
     * @return A {@link Converter} for the requested class type
     */
    <T> Class<? extends Converter<T>> getConverter(Class<T> classType);
}