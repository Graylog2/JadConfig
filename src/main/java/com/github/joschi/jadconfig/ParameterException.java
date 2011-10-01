package com.github.joschi.jadconfig;

/**
 * This exception indicates an error while processing a configuration parameter.
 *
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
