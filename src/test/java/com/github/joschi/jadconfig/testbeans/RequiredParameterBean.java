package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class RequiredParameterBean {

    @Parameter("test.string")
    private String myString;

    @Parameter(value = "test.non-existing-property", required = true)
    private String nonExisting;

    public String getMyString() {
        return myString;
    }

    public String getNonExisting() {
        return nonExisting;
    }
}
