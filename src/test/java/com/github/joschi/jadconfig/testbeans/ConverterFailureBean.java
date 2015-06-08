package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class ConverterFailureBean {

    @Parameter(value = "test.string", required = true)
    private Boolean myBool;

    public Boolean getMyBool() {
        return myBool;
    }
}
