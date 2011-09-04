package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.testconverters.FoobarConverter;

public class FoobarConfigurationBean {

    @Parameter(value = "test.string", converter = FoobarConverter.class)
    private String myString;

    public String getMyString() {
        return myString;
    }
}
