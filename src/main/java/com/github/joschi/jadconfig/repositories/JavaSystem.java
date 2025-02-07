package com.github.joschi.jadconfig.repositories;

import java.util.Properties;

/**
 * Abstraction to separate {@link java.lang.System} env and properties, to avoid static usage of the System class
 * and reliable unit testing od repositories, independent of actual underlying system.
 */
public interface JavaSystem {
    java.util.Map<String,String> getenv();
    String getenv(String name);
    Properties getProperties();
    String getProperty(String key);
}
