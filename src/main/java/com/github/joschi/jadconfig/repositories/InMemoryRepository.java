package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Repository} class providing access to a simple configuration repository backed by {@link HashMap}
 *
 * @author jschalanda
 */
public class InMemoryRepository implements Repository {

    private Map<String, String> properties = null;

    public InMemoryRepository() {
    }

    public InMemoryRepository(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public void open() throws RepositoryException {

        if (properties == null) {

            properties = new HashMap<String, String>();
        }
    }

    @Override
    public String read(String name) {

        if (properties == null) {

            throw new IllegalStateException("Repository has already been closed or has never been opened");
        }

        return properties.get(name);
    }

    @Override
    public void write(String name, String value) throws RepositoryException {

        if (properties == null) {

            throw new IllegalStateException("Repository has already been closed or has never been opened");
        }

        properties.put(name, value);
    }

    @Override
    public void save() throws RepositoryException {
        // NOP
    }

    @Override
    public void close() throws RepositoryException {

        if (properties == null) {

            throw new IllegalStateException("Repository has already been closed or has never been opened");
        }

        properties = null;
    }

    public int size() {

        if (properties == null) {

            throw new IllegalStateException("Call open before attempting any other operation");
        }

        return properties.size();
    }
}
