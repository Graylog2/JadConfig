package com.github.joschi.jadconfig.info;

import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Information about a configuration parameter: where its value came from and where it has been declared.
 * <p>
 * The value and its source are the same for all declarations, since JadConfig looks up a parameter name the same
 * way regardless of the configuration bean declaring it.
 *
 * @param name         The parameter name
 * @param source       Where the value has been read from, {@code null} if no repository provided a value and the
 *                     default value is used
 * @param value        The raw value read from the repository. Always {@code null} for {@link #isSensitive() sensitive}
 *                     parameters, the value isn't even stored in that case.
 * @param declarations All declarations of this parameter, in the order they have been processed
 */
public record ParameterInfo(
        String name,
        @Nullable ParameterSource source,
        @Nullable String value,
        List<ParameterDeclaration> declarations
) {

    public ParameterInfo {
        Objects.requireNonNull(name, "name");
        declarations = List.copyOf(declarations);
        if (declarations.stream().anyMatch(declaration -> declaration.metadata().sensitive())) {
            value = null;
        }
    }

    /**
     * Whether no repository provided a value and the default value of the configuration bean(s) is used.
     */
    public boolean isDefault() {
        return source == null;
    }

    /**
     * Whether any declaration of this parameter has been marked as sensitive.
     */
    public boolean isSensitive() {
        return declarations.stream().anyMatch(declaration -> declaration.metadata().sensitive());
    }
}
