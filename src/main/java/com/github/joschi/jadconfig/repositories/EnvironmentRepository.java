package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

/**
 * {@link Repository} class providing access to environment variables
 *
 * @author jschalanda
 * @see System#getenv()
 * @see System#getenv(String)
 */
public class EnvironmentRepository implements Repository {

    @Override
    public void open() throws RepositoryException {

        // NOP
    }

    @Override
    public String read(String name) {

        return System.getenv(name);
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

        return System.getenv().size();
    }
}
