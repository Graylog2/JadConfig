package com.github.joschi.jadconfig;

/**
 * @author jschalanda
 */
public class ParameterException extends RuntimeException {

    public ParameterException() {

        super();
    }

    public ParameterException(String s) {
        super(s);
    }

    public ParameterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ParameterException(Throwable throwable) {
        super(throwable);
    }
}
