package com.github.joschi.jadconfig.testconverters;

import com.github.joschi.jadconfig.Converter;

/**
 * Created by IntelliJ IDEA.
 * User: joschi
 * Date: 04.09.11
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class FoobarConverter implements Converter<String> {

    @Override
    public String convert(String value) {
        return "Foobar";
    }
}
