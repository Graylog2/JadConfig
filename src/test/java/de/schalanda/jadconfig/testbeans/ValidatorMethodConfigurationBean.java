package de.schalanda.jadconfig.testbeans;

import de.schalanda.jadconfig.Parameter;
import de.schalanda.jadconfig.ValidationException;
import de.schalanda.jadconfig.ValidatorMethod;

public class ValidatorMethodConfigurationBean {

    @Parameter("test.string")
    private String myString;

    @Parameter("test.byte")
    private byte myByte;

    @Parameter("test.short")
    private short myShort;

    @Parameter("test.integer")
    private int myInt;

    @Parameter("test.long")
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

    public int getMyInt() {
        return myInt;
    }

    public long getMyLong() {
        return myLong;
    }

    @ValidatorMethod
    public void validate() throws ValidationException {

        if (!"Test".equals(myString)) {
            throw new ValidationException();
        }

        if (myByte > myShort || myShort > myInt || myInt > myLong) {
            throw new ValidationException();
        }
    }
}
