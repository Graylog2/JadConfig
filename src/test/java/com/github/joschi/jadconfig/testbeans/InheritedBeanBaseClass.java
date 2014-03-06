package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

/**
 * @author Dennis Oelkers <dennis@torch.sh>
 */
public class InheritedBeanBaseClass {
    @Parameter("test.long")
    private long myInheritedLong;

    public long getMyInheritedLong() {
        return myInheritedLong;
    }

    public void setMyInheritedLong(long myInheritedLong) {
        this.myInheritedLong = myInheritedLong;
    }
}
