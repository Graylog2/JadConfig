package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class Multi1ConfigurationBean {

    @Parameter("test.string")
    private String myString;

    public String getMyString() {
        return myString;
    }
}
