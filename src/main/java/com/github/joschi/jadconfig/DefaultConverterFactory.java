package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.*;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * {@link ConverterFactory} for holding the default {@link Converter} classes.
 * <p/>
 * Supported {@link Converter} types are:
 * <ul>
 * <li>{@link String} through {@link StringConverter}</li>
 * <li>{@link Byte} and {@literal byte} through {@link ByteConverter}</li>
 * <li>{@link Short} and {@literal short} through {@link ByteConverter}</li>
 * <li>{@link Integer} and {@literal int} through {@link ByteConverter}</li>
 * <li>{@link Long} and {@literal long} through {@link ByteConverter}</li>
 * <li>{@link Boolean} and {@literal boolean} through {@link ByteConverter}</li>
 * <li>{@link Float} and {@literal float} through {@link ByteConverter}</li>
 * <li>{@link Double} and {@literal double} through {@link ByteConverter}</li>
 * <li>{@link URI} through {@link URIConverter}</li>
 * <li>{@link URL} through {@link URLConverter}</li>
 * <li>{@link File} through {@link FileConverter}</li>
 * <li>{@link BigDecimal} through {@link BigDecimalConverter}</li>
 * <li>{@link BigInteger} through {@link BigIntegerConverter}</li>
 * <li>{@link Charset} through {@link CharsetConverter}</li>
 * <li>{@link Currency} through {@link CurrencyConverter}</li>
 * <li>{@link DecimalFormat} through {@link DecimalFormatConverter}</li>
 * <li>{@link InetAddress} through {@link InetAddressConverter}</li>
 * <li>{@link Inet4Address} through {@link Inet4AddressConverter}</li>
 * <li>{@link Inet6Address} through {@link Inet6AddressConverter}</li>
 * <li>{@link InetSocketAddress} through {@link InetSocketAddressConverter}</li>
 * <li>{@link Locale} through {@link LocaleConverter}</li>
 * <li>{@link Pattern} through {@link PatternConverter}</li>
 * <li>{@link TimeZone} through {@link TimeZoneConverter}</li>
 * <li>{@link UUID} through {@link UUIDConverter}</li>
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
        DEFAULT_CONVERTERS.put(URL.class, URLConverter.class);
        DEFAULT_CONVERTERS.put(File.class, FileConverter.class);
        DEFAULT_CONVERTERS.put(BigDecimal.class, BigDecimalConverter.class);
        DEFAULT_CONVERTERS.put(BigInteger.class, BigIntegerConverter.class);
        DEFAULT_CONVERTERS.put(Charset.class, CharsetConverter.class);
        DEFAULT_CONVERTERS.put(Currency.class, CurrencyConverter.class);
        DEFAULT_CONVERTERS.put(DecimalFormat.class, DecimalFormatConverter.class);
        DEFAULT_CONVERTERS.put(InetAddress.class, InetAddressConverter.class);
        DEFAULT_CONVERTERS.put(Inet4Address.class, Inet4AddressConverter.class);
        DEFAULT_CONVERTERS.put(Inet6Address.class, Inet6AddressConverter.class);
        DEFAULT_CONVERTERS.put(InetSocketAddress.class, InetSocketAddressConverter.class);
        DEFAULT_CONVERTERS.put(Locale.class, LocaleConverter.class);
        DEFAULT_CONVERTERS.put(Pattern.class, PatternConverter.class);
        DEFAULT_CONVERTERS.put(TimeZone.class, TimeZoneConverter.class);
        DEFAULT_CONVERTERS.put(UUID.class, UUIDConverter.class);
    }

    /**
     * Finds and returns a {@link Converter} for the provided {@literal classType}.
     *
     * @param classType The class type for which to find and return a {@link Converter}
     * @return A {@link Converter} for the requested class type
     */
    public <T> Class<? extends Converter<T>> getConverter(Class<T> classType) {
        return (Class<? extends Converter<T>>) DEFAULT_CONVERTERS.get(classType);
    }
}

