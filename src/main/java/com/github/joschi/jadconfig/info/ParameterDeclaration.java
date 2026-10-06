package com.github.joschi.jadconfig.info;

import jakarta.annotation.Nullable;

import java.util.Objects;

/**
 * Describes a single declaration of a configuration parameter in a configuration bean.
 * <p>
 * The same parameter name may be declared in multiple configuration beans, each with its own type and default value.
 *
 * @param beanClass            The class of the configuration bean declaring the parameter. For inherited fields this
 *                             is the class of the bean, not the superclass declaring the field.
 * @param metadata             The metadata derived from the annotations of the field
 * @param defaultValue         The value of the field before the configuration bean has been processed for the first
 *                             time, i. e. the default value defined in the configuration bean
 * @param defaultValueAsString The default value converted to a {@link String} with the
 *                             {@link com.github.joschi.jadconfig.Converter} of the parameter
 */
public record ParameterDeclaration(
        Class<?> beanClass,
        ParameterMetadata metadata,
        @Nullable Object defaultValue,
        @Nullable String defaultValueAsString
) {

    public ParameterDeclaration {
        Objects.requireNonNull(beanClass, "beanClass");
        Objects.requireNonNull(metadata, "metadata");
    }
}
