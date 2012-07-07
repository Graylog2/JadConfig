package com.github.joschi.jadconfig;

/**
 * This exception indicates that a configuration parameter's value couldn't be validated successfully by a {@link Validator}
 *
 * @author jschalanda
 */
public class ValidationException extends Exception {

    static final long serialVersionUID = -1L;

    public ValidationException() {

        super();
    }

    public ValidationException(String s) {
        super(s);
    }

    public ValidationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }
}
