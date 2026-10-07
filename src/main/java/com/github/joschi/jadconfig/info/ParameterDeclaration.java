package com.github.joschi.jadconfig.info;

import jakarta.annotation.Nullable;

import java.util.Objects;

/**
 * Describes a single declaration of a configuration parameter in a configuration bean and how its value has been
 * resolved.
 * <p>
 * The same parameter name may be declared in multiple configuration beans, each with its own type and default value.
 * The value and its source are recorded per declaration, since declarations may resolve differently, e. g. because
 * of a different {@link com.github.joschi.jadconfig.Parameter#fallbackPropertyName() fallback property name} or
 * {@link com.github.joschi.jadconfig.Parameter#trim() trim} setting.
 * <p>
 * For {@link ParameterMetadata#sensitive() sensitive} parameters the value and the default values are always
 * {@code null}, they aren't even stored in that case.
 *
 * @param beanClass            The class of the configuration bean declaring the parameter. For inherited fields this
 *                             is the class of the bean, not the superclass declaring the field.
 * @param declaringClass       The class declaring the field, which differs from {@code beanClass} for inherited fields
 * @param metadata             The metadata derived from the annotations of the field
 * @param source               Where the value has been read from, {@code null} if no repository provided a value and
 *                             the default value is used
 * @param value                The raw value read from the repository, after trimming if enabled for the parameter
 * @param defaultValue         The value of the field before the configuration bean has been processed for the first
 *                             time, i. e. the default value defined in the configuration bean
 * @param defaultValueAsString The default value converted to a {@link String} with the
 *                             {@link com.github.joschi.jadconfig.Converter} of the parameter
 */
public record ParameterDeclaration(
        Class<?> beanClass,
        Class<?> declaringClass,
        ParameterMetadata metadata,
        @Nullable ParameterSource source,
        @Nullable String value,
        @Nullable Object defaultValue,
        @Nullable String defaultValueAsString
) {

    public ParameterDeclaration {
        Objects.requireNonNull(beanClass, "beanClass");
        Objects.requireNonNull(declaringClass, "declaringClass");
        Objects.requireNonNull(metadata, "metadata");
        if (metadata.sensitive()) {
            value = null;
            defaultValue = null;
            defaultValueAsString = null;
        }
    }

    /**
     * Whether no repository provided a value and the default value of the configuration bean is used.
     */
    public boolean isDefault() {
        return source == null;
    }
}
