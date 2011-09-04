package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.*;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link ConverterFactory} for holding the default {@link Converter} classes
 *
 * @author jschalanda
 */
public class DefaultConverterFactory implements ConverterFactory {

    private static Map<Class, Class<? extends Converter<?>>> defaultConverters;

    static {
        defaultConverters = new HashMap<Class, Class<? extends Converter<?>>>();
        defaultConverters.put(String.class, StringConverter.class);
        defaultConverters.put(Byte.class, ByteConverter.class);
        defaultConverters.put(byte.class, ByteConverter.class);
        defaultConverters.put(Short.class, ShortConverter.class);
        defaultConverters.put(short.class, ShortConverter.class);
        defaultConverters.put(Integer.class, IntegerConverter.class);
        defaultConverters.put(int.class, IntegerConverter.class);
        defaultConverters.put(Long.class, LongConverter.class);
        defaultConverters.put(long.class, LongConverter.class);
        defaultConverters.put(Boolean.class, BooleanConverter.class);
        defaultConverters.put(boolean.class, BooleanConverter.class);
        defaultConverters.put(Float.class, FloatConverter.class);
        defaultConverters.put(float.class, FloatConverter.class);
        defaultConverters.put(Double.class, DoubleConverter.class);
        defaultConverters.put(double.class, DoubleConverter.class);
        defaultConverters.put(URI.class, URIConverter.class);
        defaultConverters.put(File.class, FileConverter.class);
    }

    public Class<? extends Converter<?>> getConverter(Class classType) {
        return defaultConverters.get(classType);
    }
}

