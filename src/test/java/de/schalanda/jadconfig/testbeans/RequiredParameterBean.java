package de.schalanda.jadconfig.testbeans;

import de.schalanda.jadconfig.Parameter;

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
