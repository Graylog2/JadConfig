package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class DefaultValueConfigurationBean {

    @Parameter("test.string")
    private String myString = "Will be overwritten";

    @Parameter("test.does-not-exist")
    private byte myByte = 123;

    @Parameter("test.short")
    private short myShort;

    public String getMyString() {
        return myString;
    }

    public byte getMyByte() {
        return myByte;
    }

    public short getMyShort() {
        return myShort;
    }
}
