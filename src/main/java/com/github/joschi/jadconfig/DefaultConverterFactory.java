package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.*;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link ConverterFactory} for holding the default {@link Converter} classes.
 *
 * Supported {@link Converter} types are:
 * <ul>
 *     <li>{@link String} through {@link StringConverter}</li>
 *     <li>{@link Byte} and {@literal byte} through {@link ByteConverter}</li>
 *     <li>{@link Short} and {@literal short} through {@link ByteConverter}</li>
 *     <li>{@link Integer} and {@literal int} through {@link ByteConverter}</li>
 *     <li>{@link Long} and {@literal long} through {@link ByteConverter}</li>
 *     <li>{@link Boolean} and {@literal boolean} through {@link ByteConverter}</li>
 *     <li>{@link Float} and {@literal float} through {@link ByteConverter}</li>
 *     <li>{@link Double} and {@literal double} through {@link ByteConverter}</li>
 *     <li>{@link URI} through {@link URIConverter}</li>
 *     <li>{@link File} through {@link FileConverter}</li>
 * </ul>
 *
 * @author jschalanda
 */
public class DefaultConverterFactory implements ConverterFactory {

    private static final Map<Class, Class<? extends Converter<?>>> DEFAULT_CONVERTERS;

    static {
        DEFAULT_CONVERTERS = new HashMap<Class, Class<? extends Converter<?>>>();
        DEFAULT_CONVERTERS.put(String.class, StringConverter.class);
        DEFAULT_CONVERTERS.put(Byte.class, ByteConverter.class);
        DEFAULT_CONVERTERS.put(byte.class, ByteConverter.class);
        DEFAULT_CONVERTERS.put(Short.class, ShortConverter.class);
        DEFAULT_CONVERTERS.put(short.class, ShortConverter.class);
        DEFAULT_CONVERTERS.put(Integer.class, IntegerConverter.class);
        DEFAULT_CONVERTERS.put(int.class, IntegerConverter.class);
        DEFAULT_CONVERTERS.put(Long.class, LongConverter.class);
        DEFAULT_CONVERTERS.put(long.class, LongConverter.class);
        DEFAULT_CONVERTERS.put(Boolean.class, BooleanConverter.class);
        DEFAULT_CONVERTERS.put(boolean.class, BooleanConverter.class);
        DEFAULT_CONVERTERS.put(Float.class, FloatConverter.class);
        DEFAULT_CONVERTERS.put(float.class, FloatConverter.class);
        DEFAULT_CONVERTERS.put(Double.class, DoubleConverter.class);
        DEFAULT_CONVERTERS.put(double.class, DoubleConverter.class);
        DEFAULT_CONVERTERS.put(URI.class, URIConverter.class);
        DEFAULT_CONVERTERS.put(File.class, FileConverter.class);
    }

    /**
     * Finds and returns a {@link Converter} for the provided {@literal classType}.
     *
     * @param classType The class type for which to find and return a {@link Converter}
     * @return A {@link Converter} for the requested class type
     */
    public Class<? extends Converter<?>> getConverter(Class classType) {
        return DEFAULT_CONVERTERS.get(classType);
    }
}

