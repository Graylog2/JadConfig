package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * {@link Repository} class providing access to environment variables.
 * <p/>
 * All keys are being looked up in upper case unless deactivated with the appropriate
 * constructor ({@link EnvironmentRepository#EnvironmentRepository(boolean)}.
 * <p/>
 * A prefix for all key lookups can be defined with {@link #EnvironmentRepository(String)}.
 * The default prefix is empty.
 *
 * @see java.lang.System#getenv()
 * @see java.lang.System#getenv(String)
 */
public class EnvironmentRepository implements Repository {
    private final String prefix;
    private final boolean upperCase;
    private final JavaSystem system;

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
        this(prefix, upperCase, JavaLangSystem.INSTANCE);
    }

    public EnvironmentRepository(String prefix, boolean upperCase, JavaSystem system) {
        this.prefix = prefix;
        this.upperCase = upperCase;
        this.system = system;
    }

    @Override
    public void open() throws RepositoryException {
        // NOP
    }

    @Override
    public String read(final String name) {
        final String envName = constructPropertyName(name);
        return system.getenv(envName);
    }

    @Override
    public Collection<String> readNames(String namePrefix) {
        final String envName = constructPropertyName(namePrefix);
        return system.getenv().keySet().stream().filter(e -> e.startsWith(envName)).collect(Collectors.toSet());
    }

    private String constructPropertyName(String name) {
        if (upperCase) {
            return  (prefix + name).toUpperCase();
        } else {
            return prefix + name;
        }
    }

    @Override
    public void close() {
        // NOP
    }

    public int size() {
        return system.getenv().size();
    }
}
