package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class DefaultValueConfigurationBean {

    @Parameter("test.string")
    private String myString;

    @Parameter("test.does-not-exist")
    private byte myByte = 123;

    public String getMyString() {
        return myString;
    }

    public byte getMyByte() {
        return myByte;
    }
}
