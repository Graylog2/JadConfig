package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

import java.io.File;
import java.net.URI;

public class SaveMeBean {

    @Parameter("save.me.string")
    private String myString;

    @Parameter("save.me.integer")
    private Integer myInteger;

    @Parameter("save.me.uri")
    private URI myUri;

    @Parameter("save.me.file")
    private File myFile;

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public Integer getMyInteger() {
        return myInteger;
    }

    public void setMyInteger(Integer myInteger) {
        this.myInteger = myInteger;
    }

    public URI getMyUri() {
        return myUri;
    }

    public void setMyUri(URI myUri) {
        this.myUri = myUri;
    }

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }
}
