package com.github.joschi.jadconfig.documentation;

import jakarta.annotation.Nullable;

/**
 * @param configurationBean Class that defines this configuration entry
 * @param fieldName java field name
 * @param type Java type name, e.g. String or Integer.
 * @param configName configuration property name, as written in the config file
 * @param defaultValue default value declared in the java field, null if not defined
 * @param required if the configuration property is mandatory (needs default or entry in the config file)
 * @param documentation textual documentation of this configuration propery
 */
public record ConfigurationEntry(
        Class<?> configurationBean,
        String fieldName,
        String type,
        String configName,
        @Nullable Object defaultValue,
        boolean required,
        String documentation
) {

    public boolean hasPriority() {
        return required && defaultValue == null;
    }
}
