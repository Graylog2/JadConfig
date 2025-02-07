package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link Repository} class providing access to a simple configuration repository backed by {@link HashMap}
 *
 * @author jschalanda
 */
public class InMemoryRepository implements Repository {

    private final Map<String, String> properties;

    public InMemoryRepository() {
        this(new LinkedHashMap<>());
    }

    public InMemoryRepository(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public void open() throws RepositoryException {
    }

    @Override
    public String read(String name) {
        return properties.get(name);
    }

    @Override
    public Collection<String> readNames(String prefix) {
        return properties.keySet().stream().filter(key -> key.startsWith(prefix)).collect(Collectors.toSet());
    }

    @Override
    public void close() {
    }
}
