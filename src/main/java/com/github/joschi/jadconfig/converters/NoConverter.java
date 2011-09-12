package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;

/**
 * No-operation converter being used as default for {@link com.github.joschi.jadconfig.Parameter#converter()}.
 * Always throws an {@link UnsupportedOperationException}
 *
 * @author jschalanda
 */
public class NoConverter implements Converter<String> {

    @Override
    public String convertFrom(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertTo(String value) {
        throw new UnsupportedOperationException();
    }
}
