package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

/**
 * @author Dennis Oelkers <dennis@torch.sh>
 */
public class InheritedBeanSubClass extends InheritedBeanBaseClass {
    @Parameter("test.string")
    private String myString;

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
