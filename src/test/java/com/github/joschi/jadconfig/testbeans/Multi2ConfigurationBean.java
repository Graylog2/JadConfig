package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class Multi2ConfigurationBean {

    @Parameter("test.byte")
    private byte myByte;

    public byte getMyByte() {
        return myByte;
    }
}
