package com.github.joschi.jadconfig;

import java.util.Collection;

/**
 * Interface for configuration repositories
 * <p>A configuration repository can be any data source containing configuration data,
 * e. g. a simple in-memory store, a file, or a database.</p>
 *
 * @author jschalanda
 */
public interface Repository {

    /**
     * Opens the configuration repository, e. g. create a database connection, open a file on disk for reading
     *
     * @throws RepositoryException If an error occurred while opening the data source
     */
    void open() throws RepositoryException;

    /**
     * Reads the configuration parameter {@literal name} from the backing data source. The {@literal name} is specific
     * to the underlying data source.
     *
     * @param name The parameter name
     * @return The value of the provided {@literal name} as {@link String}
     */
    String read(String name);

    Collection<String> readNames(String prefix);

    /**
     * Closes the underlying data source when it isn't require any more.
     *
     * @throws RepositoryException If an error occurred while closing the underlying data source
     */
    void close() throws RepositoryException;
}
