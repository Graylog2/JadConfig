package com.github.joschi.jadconfig.guava;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ConverterFactory;
import com.github.joschi.jadconfig.guava.converters.CacheBuilderSpecConverter;
import com.github.joschi.jadconfig.guava.converters.HashCodeConverter;
import com.github.joschi.jadconfig.guava.converters.HostAndPortConverter;
import com.github.joschi.jadconfig.guava.converters.HostSpecifierConverter;
import com.github.joschi.jadconfig.guava.converters.InternetDomainNameConverter;
import com.github.joschi.jadconfig.guava.converters.MediaTypeConverter;
import com.github.joschi.jadconfig.guava.converters.UnsignedIntegerConverter;
import com.github.joschi.jadconfig.guava.converters.UnsignedLongConverter;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.hash.HashCode;
import com.google.common.net.HostAndPort;
import com.google.common.net.HostSpecifier;
import com.google.common.net.InternetDomainName;
import com.google.common.net.MediaType;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ConverterFactory} for holding the {@link Converter} classes for Google Guava support.
 * <p>
 * Supported {@link Converter} types are:
 * <ul>
 * <li>{@link CacheBuilderSpec} through {@link CacheBuilderSpecConverter}</li>
 * <li>{@link HashCode} through {@link HashCodeConverter}</li>
 * <li>{@link HostAndPort} through {@link HostAndPortConverter}</li>
 * <li>{@link HostSpecifier} through {@link HostSpecifierConverter}</li>
 * <li>{@link InternetDomainName} through {@link InternetDomainNameConverter}</li>
 * <li>{@link MediaType} through {@link MediaTypeConverter}</li>
 * <li>{@link UnsignedInteger} through {@link UnsignedIntegerConverter}</li>
 * <li>{@link UnsignedLong} through {@link UnsignedLongConverter}</li>
 * </ul>
 * </p>
 */
public class GuavaConverterFactory implements ConverterFactory {
    private static final Map<Class, Class<? extends Converter<?>>> GUAVA_CONVERTERS;

    static {
        GUAVA_CONVERTERS = new HashMap<Class, Class<? extends Converter<?>>>();
        GUAVA_CONVERTERS.put(CacheBuilderSpec.class, CacheBuilderSpecConverter.class);
        GUAVA_CONVERTERS.put(HashCode.class, HashCodeConverter.class);
        GUAVA_CONVERTERS.put(HostAndPort.class, HostAndPortConverter.class);
        GUAVA_CONVERTERS.put(HostSpecifier.class, HostSpecifierConverter.class);
        GUAVA_CONVERTERS.put(InternetDomainName.class, InternetDomainNameConverter.class);
        GUAVA_CONVERTERS.put(MediaType.class, MediaTypeConverter.class);
        GUAVA_CONVERTERS.put(UnsignedInteger.class, UnsignedIntegerConverter.class);
        GUAVA_CONVERTERS.put(UnsignedLong.class, UnsignedLongConverter.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> Class<? extends Converter<T>> getConverter(Class<T> classType) {
        return (Class<? extends Converter<T>>) GUAVA_CONVERTERS.get(classType);
    }
}