package de.schalanda.jadconfig.testbeans;

import de.schalanda.jadconfig.Parameter;

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
