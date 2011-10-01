package com.github.joschi.jadconfig.testconverters;

import com.github.joschi.jadconfig.Converter;

/**
 * @author jschalanda
 */
public class FoobarConverter implements Converter<String> {

    @Override
    public String convertFrom(String value) {
        return "Foobar";
    }

    @Override
    public String convertTo(String value) {
        return value;
    }
}
