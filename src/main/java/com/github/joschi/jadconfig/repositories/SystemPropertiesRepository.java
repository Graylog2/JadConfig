package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * {@link Repository} class providing access to System properties.
 * <p/>
 * A prefix for all key lookups can be defined with {@link #SystemPropertiesRepository(String)}.
 * The default prefix is empty.
 *
 * @author jschalanda
 * @see java.lang.System#getProperties()
 * @see java.lang.System#getProperty(String)
 */
public class SystemPropertiesRepository implements Repository {

    private final String prefix;
    private final JavaSystem system;

    /**
     * Creates a new instance of {@link SystemPropertiesRepository} with the default settings,
     * i. e. no prefix.
     */
    public SystemPropertiesRepository() {
        this("");
    }


    public SystemPropertiesRepository(String prefix) {
        this(prefix, JavaLangSystem.INSTANCE);
    }


    /**
     * Creates a new instance of {@link SystemPropertiesRepository} with the given prefix.
     *
     * @param prefix The prefix used for key lookups, e. g. {@code "custom."}.
     */
    public SystemPropertiesRepository(final String prefix, JavaSystem system) {
        this.prefix = prefix;
        this.system = system;
    }

    @Override
    public void open() throws RepositoryException {
        // NOP
    }

    @Override
    public String read(String name) {
        return system.getProperty(prefix + name);
    }

    @Override
    public Collection<String> readNames(String namePrefix) {
        return system.getProperties().stringPropertyNames().stream()
                .filter(key -> key.startsWith(prefix + namePrefix))
                .map(propertyName -> propertyName.replaceFirst(prefix, ""))
                .collect(Collectors.toSet());
    }

    @Override
    public void close() {
        // NOP
    }

}
