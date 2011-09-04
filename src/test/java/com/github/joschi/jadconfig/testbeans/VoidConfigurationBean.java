package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class VoidConfigurationBean {

    @Parameter("test.string")
    private Void myVoid;

    public Void getMyVoid() {
        return myVoid;
    }
}
