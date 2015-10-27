package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.ValidatorMethod;
import com.github.joschi.jadconfig.validators.InetPortValidator;
import com.github.joschi.jadconfig.validators.NoValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import com.github.joschi.jadconfig.validators.PositiveLongValidator;
import com.github.joschi.jadconfig.validators.PositiveSizeValidator;

public class ValidatedConfigurationBean {

    @Parameter(value = "test.string", validators = NoValidator.class)
    private String myString;

    @Parameter("test.byte")
    private byte myByte;

    @Parameter("test.short")
    private short myShort;

    @Parameter(value = "test.integer", validators = {PositiveIntegerValidator.class})
    private int myInt;

    @Parameter(value = "test.integer.port", validators = {PositiveIntegerValidator.class, InetPortValidator.class})
    private int myInetPort;

    @Parameter(value = "test.long", validator = PositiveLongValidator.class)
    private long myLong;

    public String getMyString() {
        return myString;
    }

    public byte getMyByte() {
        return myByte;
    }

    public short getMyShort() {
        return myShort;
    }

    public int getMyInetPort() {
        return myInetPort;
    }

    public int getMyInt() {
        return myInt;
    }

    public long getMyLong() {
        return myLong;
    }

    @ValidatorMethod
    public void validate() throws ValidationException {

        if (!"Test".equals(myString)) {
            throw new ValidationException("BOOM");
        }

        if (myByte > myShort || myShort > myInt || myInt > myLong) {
            throw new ValidationException();
        }
    }
}
