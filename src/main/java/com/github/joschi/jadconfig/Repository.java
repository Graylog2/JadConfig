package com.github.joschi.jadconfig;

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

    /**
     * Returns a human readable description of where the parameter {@literal name} is being read from, e. g.
     * {@code "environment variable GRAYLOG_HTTP_BIND_ADDRESS"}.
     *
     * @param name The parameter name
     * @return A description of the source of the parameter
     */
    default String describeSource(String name) {
        return getClass().getSimpleName();
    }

    /**
     * Closes the underlying data source when it isn't require any more.
     *
     * @throws RepositoryException If an error occurred while closing the underlying data source
     */
    void close() throws RepositoryException;
}
