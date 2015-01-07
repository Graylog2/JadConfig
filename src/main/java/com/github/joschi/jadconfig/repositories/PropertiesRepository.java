package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * {@link Repository} class providing access to a configuration repository backed by {@link Properties} files
 *
 * @author jschalanda
 */
public class PropertiesRepository implements Repository {

    private final Properties PROPERTIES = new Properties();
    private File propertiesFile = null;

    /**
     * Creates a new {@link PropertiesRepository} from the provided properties file {@literal filename}
     *
     * @param filename The filename of the properties file
     * @throws IllegalArgumentException If the provided {@literal filename} is null or empty
     */
    public PropertiesRepository(String filename) {

        if (filename == null || "".equals(filename)) {

            throw new IllegalArgumentException("Properties filename must not be null or empty!");
        }

        this.propertiesFile = new File(filename);
    }

    /**
     * Creates a new {@link PropertiesRepository} from the provided {@literal propertiesFile}
     *
     * @param propertiesFile The properties {@link File}
     * @throws IllegalArgumentException If the provided {@link File} is null
     */
    public PropertiesRepository(File propertiesFile) {

        if (propertiesFile == null) {

            throw new IllegalArgumentException("Properties file must not be null!");
        }

        this.propertiesFile = propertiesFile;
    }

    /**
     * Opens the properties file and reads its {@link Properties}
     *
     * @throws RepositoryException If the {@literal propertiesFile} is null, doesn't exist or an error occurred while
     *                             reading the properties file.
     */
    @Override
    public void open() throws RepositoryException {

        if (propertiesFile == null) {

            throw new RepositoryException("Properties file must not be null!");
        }

        if (!propertiesFile.exists()) {

            throw new RepositoryException("Properties file " + propertiesFile.getPath() + " doesn't exist!");
        }

        Reader propertiesReader = null;

        try {
            propertiesReader = new FileReader(propertiesFile);
            PROPERTIES.load(propertiesReader);
        } catch (IOException ex) {
            throw new RepositoryException("Couldn't open properties file: " + propertiesFile, ex);
        } finally {
            if(propertiesReader != null) {
                try {
                    propertiesReader.close();
                } catch (IOException e) {
                    // Intentionally empty
                }
            }
        }
    }

    /**
     * Returns the value for a property {@literal name} or {@literal null}
     *
     * @param name The parameter name
     * @return The property value for {@literal name} or {@literal null}
     */
    @Override
    public String read(String name) {

        return PROPERTIES.getProperty(name);
    }

    /**
     * Close the underlying properties file.
     *
     * @throws RepositoryException
     */
    @Override
    public void close() throws RepositoryException {
    }

    public File getPropertiesFile() {
        return propertiesFile;
    }

    public void setPropertiesFile(File propertiesFile) {
        this.propertiesFile = propertiesFile;
    }
}
