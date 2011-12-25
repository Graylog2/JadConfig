package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

/**
 * {@link Repository} class providing access to System properties
 *
 * @author jschalanda
 * @see System#getProperties()
 * @see System#getProperty(String)
 */
public class SystemPropertiesRepository implements Repository {

    @Override
    public void open() throws RepositoryException {

        // NOP
    }

    @Override
    public String read(String name) {

        return System.getProperty(name);
    }

    @Override
    public void write(String name, String value) throws RepositoryException {

        // NOP
    }

    @Override
    public void save() throws RepositoryException {

        // NOP
    }

    @Override
    public void close() throws RepositoryException {

        // NOP
    }

    public int size() {

        return System.getProperties().size();
    }
}
