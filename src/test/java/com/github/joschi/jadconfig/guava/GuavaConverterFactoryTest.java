package com.github.joschi.jadconfig.guava;

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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuavaConverterFactoryTest {
    @Test
    public void testConverterMap() {
        final GuavaConverterFactory factory = new GuavaConverterFactory();

        assertEquals(CacheBuilderSpecConverter.class, factory.getConverter(CacheBuilderSpec.class));
        assertEquals(HashCodeConverter.class, factory.getConverter(HashCode.class));
        assertEquals(HostAndPortConverter.class, factory.getConverter(HostAndPort.class));
        assertEquals(HostSpecifierConverter.class, factory.getConverter(HostSpecifier.class));
        assertEquals(InternetDomainNameConverter.class, factory.getConverter(InternetDomainName.class));
        assertEquals(MediaTypeConverter.class, factory.getConverter(MediaType.class));
        assertEquals(UnsignedIntegerConverter.class, factory.getConverter(UnsignedInteger.class));
        assertEquals(UnsignedLongConverter.class, factory.getConverter(UnsignedLong.class));
    }
}
