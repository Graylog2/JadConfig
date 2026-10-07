package com.github.joschi.jadconfig.info;

import com.github.joschi.jadconfig.RestartRequirement;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Information about a configuration parameter: where it has been declared and where its value came from.
 * <p>
 * All declarations look up the parameter name the same way, but they may still resolve differently, e. g. if they
 * define different {@link com.github.joschi.jadconfig.Parameter#fallbackPropertyName() fallback property names}.
 * {@link #source()} and {@link #value()} are therefore only a summary, see {@link #declarations()} for the
 * resolution of each declaration.
 *
 * @param name         The parameter name
 * @param declarations All declarations of this parameter, in the order they have been processed
 */
public record ParameterInfo(
        String name,
        List<ParameterDeclaration> declarations
) {

    public ParameterInfo {
        Objects.requireNonNull(name, "name");
        declarations = List.copyOf(declarations);
    }

    /**
     * The source of the first declaration which got its value from a repository, {@code null} if no declaration did.
     *
     * @see ParameterDeclaration#source()
     */
    @Nullable
    public ParameterSource source() {
        return firstResolvedDeclaration().map(ParameterDeclaration::source).orElse(null);
    }

    /**
     * The value of the first declaration which got its value from a repository, {@code null} if no declaration did.
     * Always {@code null} for {@link #isSensitive() sensitive} parameters.
     *
     * @see ParameterDeclaration#value()
     */
    @Nullable
    public String value() {
        if (isSensitive()) {
            return null;
        }
        return firstResolvedDeclaration().map(ParameterDeclaration::value).orElse(null);
    }

    /**
     * Whether no repository provided a value for any declaration, i. e. the default values of the configuration
     * bean(s) are used.
     */
    public boolean isDefault() {
        return declarations.stream().allMatch(ParameterDeclaration::isDefault);
    }

    /**
     * Whether any declaration of this parameter has been marked as sensitive.
     */
    public boolean isSensitive() {
        return declarations.stream().anyMatch(declaration -> declaration.metadata().sensitive());
    }

    /**
     * Whether changing this parameter requires a restart, combined over all declarations:
     * {@link RestartRequirement#REQUIRED} if any declaration requires a restart,
     * {@link RestartRequirement#NOT_REQUIRED} if all declarations don't require one and
     * {@link RestartRequirement#UNKNOWN} otherwise.
     *
     * @see com.github.joschi.jadconfig.Parameter#requiresRestart()
     */
    public RestartRequirement requiresRestart() {
        final List<RestartRequirement> requirements = declarations.stream()
                .map(declaration -> declaration.metadata().requiresRestart())
                .toList();
        if (requirements.contains(RestartRequirement.REQUIRED)) {
            return RestartRequirement.REQUIRED;
        }
        if (!requirements.isEmpty() && requirements.stream().allMatch(RestartRequirement.NOT_REQUIRED::equals)) {
            return RestartRequirement.NOT_REQUIRED;
        }
        return RestartRequirement.UNKNOWN;
    }

    private Optional<ParameterDeclaration> firstResolvedDeclaration() {
        return declarations.stream()
                .filter(declaration -> !declaration.isDefault())
                .findFirst();
    }
}
