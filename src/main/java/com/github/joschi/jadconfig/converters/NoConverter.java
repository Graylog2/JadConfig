package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;

/**
 * No-operation converter being used as default for {@link com.github.joschi.jadconfig.Parameter#converter()}.
 * Always throws an {@link UnsupportedOperationException}.
 *
 * @author jschalanda
 */
public class NoConverter implements Converter<String> {

    /**
     * Default {@link Converter#convertFrom(String)} implementation. Always throws {@link UnsupportedOperationException}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link String} instance representing the configuration parameter's value
     */
    @Override
    public String convertFrom(String value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Default {@link Converter#convertTo(Object)} implementation. Always throws {@link UnsupportedOperationException}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(String value) {
        throw new UnsupportedOperationException();
    }
}
