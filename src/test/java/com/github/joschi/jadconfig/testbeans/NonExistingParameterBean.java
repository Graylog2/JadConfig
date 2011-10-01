package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

public class NonExistingParameterBean {

    @Parameter("test.string")
    private String myString;

    @Parameter("test.non-existing-property")
    private String nonExisting;

    public String getMyString() {
        return myString;
    }

    public String getNonExisting() {
        return nonExisting;
    }
}
