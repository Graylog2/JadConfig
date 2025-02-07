package com.github.joschi.jadconfig.repositories;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This implementation of {@link JavaSystem} allows defining exact system {@code env} and {@code properties} for unit
 * test purposes.
 */
public class TestSystem implements JavaSystem {

    private final Map<String, String> env = new LinkedHashMap<>();
    private final Properties properties = new Properties();

    public TestSystem putEnv(String key, String value) {
        env.put(key, value);
        return this;
    }

    @Override
    public Map<String, String> getenv() {
        return env;
    }

    @Override
    public String getenv(String name) {
        return env.get(name);
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public TestSystem putProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }
}
