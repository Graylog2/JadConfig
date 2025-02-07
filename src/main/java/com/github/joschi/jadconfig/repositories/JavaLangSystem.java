package com.github.joschi.jadconfig.repositories;

import java.util.Map;
import java.util.Properties;

/**
 * Delegates all env and properties calls to the underlying {@link java.lang.System}
 */
public class JavaLangSystem implements JavaSystem {

    public static final JavaSystem INSTANCE = new JavaLangSystem();

    @Override
    public Map<String, String> getenv() {
        return java.lang.System.getenv();
    }

    @Override
    public String getenv(String name) {
        return java.lang.System.getenv(name);
    }

    @Override
    public Properties getProperties() {
        return java.lang.System.getProperties();
    }

    @Override
    public String getProperty(String key) {
        return java.lang.System.getProperty(key);
    }
}
