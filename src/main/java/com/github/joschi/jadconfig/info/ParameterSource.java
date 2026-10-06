package com.github.joschi.jadconfig.info;

import com.github.joschi.jadconfig.Repository;

import java.util.Objects;

/**
 * Describes where the value of a configuration parameter has been read from.
 *
 * @param repository   The {@link Repository} which provided the value
 * @param propertyName The property name which has been looked up in the {@link Repository}. This is either the
 *                     parameter name itself or its
 *                     {@link com.github.joschi.jadconfig.Parameter#fallbackPropertyName() fallback property name}.
 * @param description  Human readable description of the source, e. g.
 *                     {@code "environment variable GRAYLOG_HTTP_BIND_ADDRESS"}
 * @see Repository#describeSource(String)
 */
public record ParameterSource(Repository repository, String propertyName, String description) {

    public ParameterSource {
        Objects.requireNonNull(repository, "repository");
        Objects.requireNonNull(propertyName, "propertyName");
    }
}
