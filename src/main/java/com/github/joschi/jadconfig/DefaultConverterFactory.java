package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.BigDecimalConverter;
import com.github.joschi.jadconfig.converters.BigIntegerConverter;
import com.github.joschi.jadconfig.converters.BooleanConverter;
import com.github.joschi.jadconfig.converters.ByteConverter;
import com.github.joschi.jadconfig.converters.CharsetConverter;
import com.github.joschi.jadconfig.converters.CurrencyConverter;
import com.github.joschi.jadconfig.converters.DecimalFormatConverter;
import com.github.joschi.jadconfig.converters.DoubleConverter;
import com.github.joschi.jadconfig.converters.DurationConverter;
import com.github.joschi.jadconfig.converters.FileConverter;
import com.github.joschi.jadconfig.converters.FloatConverter;
import com.github.joschi.jadconfig.converters.Inet4AddressConverter;
import com.github.joschi.jadconfig.converters.Inet6AddressConverter;
import com.github.joschi.jadconfig.converters.InetAddressConverter;
import com.github.joschi.jadconfig.converters.InetSocketAddressConverter;
import com.github.joschi.jadconfig.converters.IntegerConverter;
import com.github.joschi.jadconfig.converters.LocaleConverter;
import com.github.joschi.jadconfig.converters.LongConverter;
import com.github.joschi.jadconfig.converters.PatternConverter;
import com.github.joschi.jadconfig.converters.ShortConverter;
import com.github.joschi.jadconfig.converters.SizeConverter;
import com.github.joschi.jadconfig.converters.SizeUnitConverter;
import com.github.joschi.jadconfig.converters.StringConverter;
import com.github.joschi.jadconfig.converters.TimeZoneConverter;
import com.github.joschi.jadconfig.converters.URIConverter;
import com.github.joschi.jadconfig.converters.URLConverter;
import com.github.joschi.jadconfig.converters.UUIDConverter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.util.Size;
import com.github.joschi.jadconfig.util.SizeUnit;

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
 * <p></p>Supported {@link Converter} types are:
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
 * <li>{@link Duration} through {@link DurationConverter}</li>
 * <li>{@link Size} through {@link SizeConverter}</li>
 * <li>{@link SizeUnit} through {@link SizeUnitConverter}</li>
 * </ul>
 * </p>
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
        DEFAULT_CONVERTERS.put(Duration.class, DurationConverter.class);
        DEFAULT_CONVERTERS.put(Size.class, SizeConverter.class);
        DEFAULT_CONVERTERS.put(SizeUnit.class, SizeUnitConverter.class);
    }

    /**
     * Finds and returns a {@link Converter} for the provided {@literal classType}.
     *
     * @param classType The class type for which to find and return a {@link Converter}
     * @return A {@link Converter} for the requested class type
     */
    @SuppressWarnings("unchecked")
    public <T> Class<? extends Converter<T>> getConverter(Class<T> classType) {
        return (Class<? extends Converter<T>>) DEFAULT_CONVERTERS.get(classType);
    }
}

