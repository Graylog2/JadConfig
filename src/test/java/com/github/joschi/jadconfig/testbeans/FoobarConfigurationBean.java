package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.testconverters.FoobarConverter;

/**
 * Created by IntelliJ IDEA.
 * User: joschi
 * Date: 04.09.11
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
public class FoobarConfigurationBean {

    @Parameter(value = "test.string", converter = FoobarConverter.class)
    private String myString;

    public String getMyString() {
        return myString;
    }
}
