package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.converters.StringListConverter;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;

public class SimpleConfigurationBean {

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

    @Parameter("test.float")
    private float myFloat;

    @Parameter("test.double")
    private double myDouble;

    @Parameter("test.boolean")
    private boolean myBoolean;

    @Parameter(value = "test.list", converter = StringListConverter.class)
    private List<String> stringList;

    @Parameter("test.uri")
    private URI uri;

    @Parameter("test.file")
    private File file;

    @Parameter("test.path")
    private Path path;

    @Parameter(value = "test.Nonexistent", fallbackPropertyName = "test.string")
    private String myFallbackString;

    @Parameter(value = "test.fallback.primary", fallbackPropertyName = "test.fallback.secondary")
    private String myPrimSecString;

    @Parameter(value = "test.Nonexistent2", fallbackPropertyName = "test.AlsoNonexistent")
    private String myNonexistentString;

    @Parameter(value = "test.Nonexistent3", fallbackPropertyName = "test.fallback.secondary")
    private String myHardcodedDefaultString = "foobar";

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

    public float getMyFloat() {
        return myFloat;
    }

    public double getMyDouble() {
        return myDouble;
    }

    public boolean isMyBoolean() {
        return myBoolean;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public URI getUri() {
        return uri;
    }

    public File getFile() {
        return file;
    }

    public Path getPath() {
        return path;
    }

    public String getMyFallbackString() {
        return myFallbackString;
    }

    public String getMyPrimSecString() {
        return myPrimSecString;
    }

    public String getMyNonexistentString() {
        return myNonexistentString;
    }

    public String getMyHardcodedDefaultString() {
        return myHardcodedDefaultString;
    }
}
