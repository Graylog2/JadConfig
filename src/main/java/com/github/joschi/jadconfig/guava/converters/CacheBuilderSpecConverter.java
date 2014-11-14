package com.github.joschi.jadconfig.guava.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.cache.CacheBuilderSpec;

/**
 * Converter for type {@link CacheBuilderSpec}.
 */
public class CacheBuilderSpecConverter implements Converter<CacheBuilderSpec> {
    /**
     * Returns a {@link CacheBuilderSpec} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link CacheBuilderSpec} instance representing the configuration parameter's value
     */
    @Override
    public CacheBuilderSpec convertFrom(String value) {
        try {
            return CacheBuilderSpec.parse(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to CacheBuilderSpec.", ex);
        }
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link CacheBuilderSpec} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(CacheBuilderSpec value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toParsableString();
    }
}
