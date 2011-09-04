package com.github.joschi.jadconfig;

/**
 * @author jschalanda
 */
public class RepositoryException extends Exception {

    public RepositoryException() {

        super();
    }

    public RepositoryException(String s) {
        super(s);
    }

    public RepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RepositoryException(Throwable throwable) {
        super(throwable);
    }
}
