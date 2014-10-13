package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

/**
 * {@link Repository} class providing access to environment variables.
 * <p/>
 * All keys are being looked up in upper case unless deactivated with the appropriate
 * constructor ({@link EnvironmentRepository#EnvironmentRepository(boolean)}.
 * <p/>
 * A prefix for all key lookups can be defined with {@link #EnvironmentRepository(String)}.
 * The default prefix is empty.
 *
 * @see System#getenv()
 * @see System#getenv(String)
 */
public class EnvironmentRepository implements Repository {

    private final String prefix;
    private final boolean upperCase;

    /**
     * Creates a new instance of {@link EnvironmentRepository} with the default settings,
     * i. e. no prefix and keys looked up in upper case.
     */
    public EnvironmentRepository() {
        this("");
    }

    /**
     * Creates a new instance of {@link EnvironmentRepository} with the given prefix.
     *
     * @param prefix The prefix used for key lookups, e. g. {@code "MY_APP_"}.
     */
    public EnvironmentRepository(final String prefix) {
        this(prefix, true);
    }

    /**
     * Creates a new instance of {@link EnvironmentRepository} with an empty prefix.
     *
     * @param upperCase Whether keys should be looked up in upper case.
     */
    public EnvironmentRepository(final boolean upperCase) {
        this("", upperCase);
    }

    /**
     * Creates a new instance of {@link EnvironmentRepository} with the given prefix.
     *
     * @param prefix The prefix used for key lookups, e. g. {@code "MY_APP_"}.
     * @param upperCase Whether keys should be looked up in upper case.
     */
    public EnvironmentRepository(final String prefix, boolean upperCase) {
        this.prefix = prefix;
        this.upperCase = upperCase;
    }

    @Override
    public void open() throws RepositoryException {
        // NOP
    }

    @Override
    public String read(final String name) {
        final String envName;

        if (upperCase) {
            envName = (prefix + name).toUpperCase();
        } else {
            envName = prefix + name;
        }

        return System.getenv(envName);
    }

    @Override
    public void close() throws RepositoryException {
        // NOP
    }

    public int size() {
        return System.getenv().size();
    }
}
