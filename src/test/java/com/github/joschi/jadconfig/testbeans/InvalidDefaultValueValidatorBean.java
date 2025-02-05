package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.validators.PositiveLongValidator;

public class InvalidDefaultValueValidatorBean {

    @Parameter(value = "test.positive.long", validator = PositiveLongValidator.class)
    private long myPositiveLong = -1;

    public long getMyPositiveLong() {
        return myPositiveLong;
    }
}
