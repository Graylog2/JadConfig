package com.github.joschi.jadconfig;

/**
 * Interface for configuration repositories
 * <p/>
 * A configuration repository can be any data source containing configuration data, e. g. a simple in-memory store,
 * a file, or a database.
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
     * Writes the value {@literal value} for configuration parameter {@literal name} to the underlying data source.
     * <p/>
     * Depending on the data source this method might also just collect changed configuration parameters and finally
     * write them to the underlying data source when the {@link #save()} method is being called.
     *
     * @param name  The parameter name
     * @param value The parameter value
     * @throws RepositoryException If an error occurred while writing to the data source
     * @see #save()
     */
    void write(String name, String value) throws RepositoryException;

    /**
     * Saves any changed configuration parameters to the underlying data source.
     *
     * @throws RepositoryException If an error occurred while writing to the data source
     * @see #write(String, String)
     */
    void save() throws RepositoryException;

    /**
     * Closes the underlying data source when it isn't require any more.
     *
     * @throws RepositoryException If an error occurred while closing the underlying data source
     */
    void close() throws RepositoryException;
}
