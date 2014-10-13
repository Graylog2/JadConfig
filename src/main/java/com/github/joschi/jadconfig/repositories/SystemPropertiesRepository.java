package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

/**
 * {@link Repository} class providing access to System properties.
 * <p/>
 * A prefix for all key lookups can be defined with {@link #SystemPropertiesRepository(String)}.
 * The default prefix is empty.
 *
 * @author jschalanda
 * @see System#getProperties()
 * @see System#getProperty(String)
 */
public class SystemPropertiesRepository implements Repository {

    private final String prefix;

    /**
     * Creates a new instance of {@link SystemPropertiesRepository} with the default settings,
     * i. e. no prefix.
     */
    public SystemPropertiesRepository() {
        this("");
    }

    /**
     * Creates a new instance of {@link SystemPropertiesRepository} with the given prefix.
     *
     * @param prefix The prefix used for key lookups, e. g. {@code "custom."}.
     */
    public SystemPropertiesRepository(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void open() throws RepositoryException {

        // NOP
    }

    @Override
    public String read(String name) {

        return System.getProperty(prefix + name);
    }

    @Override
    public void close() throws RepositoryException {

        // NOP
    }

    public int size() {

        return System.getProperties().size();
    }
}
