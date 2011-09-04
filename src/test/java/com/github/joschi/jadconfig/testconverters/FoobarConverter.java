package com.github.joschi.jadconfig.testconverters;

import com.github.joschi.jadconfig.Converter;

/**
 * @author jschalanda
 */
public class FoobarConverter implements Converter<String> {

    @Override
    public String convert(String value) {
        return "Foobar";
    }
}
