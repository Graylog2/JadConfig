package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class TrimBean {
    @Parameter("test.string.ltrim")
    private String trimmedString;

    @Parameter(value = "test.string.rtrim", trim = false)
    private String untrimmedString;

    @Parameter("test.integer.trim")
    private int myInt;

    public String getTrimmedString() {
        return trimmedString;
    }

    public String getUntrimmedString() {
        return untrimmedString;
    }

    public int getMyInt() {
        return myInt;
    }
}
