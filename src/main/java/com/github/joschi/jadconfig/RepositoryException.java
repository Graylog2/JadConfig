package com.github.joschi.jadconfig;

/**
 * This exception indicates that an error occurred while using a {@link Repository}.
 *
 * @author jschalanda
 */
public class RepositoryException extends Exception {

    static final long serialVersionUID = -1L;

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
