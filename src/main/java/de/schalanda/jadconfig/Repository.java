package de.schalanda.jadconfig;

/**
 * @author jschalanda
 */
public interface Repository {

    void open() throws RepositoryException;
    String read(String name);
    void write(String name, String value) throws RepositoryException;
    void save() throws RepositoryException;
    void close() throws RepositoryException;
}
