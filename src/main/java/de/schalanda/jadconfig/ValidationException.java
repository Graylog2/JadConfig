package de.schalanda.jadconfig;

/**
 * @author jschalanda
 */
public class ValidationException extends Exception {

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
