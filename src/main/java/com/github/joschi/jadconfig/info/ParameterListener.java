package com.github.joschi.jadconfig.info;

import jakarta.annotation.Nullable;

/**
 * Callback notified by {@link com.github.joschi.jadconfig.JadConfig} for every configuration parameter which has
 * been processed successfully.
 * <p>
 * Configuration beans may be processed multiple times, so implementations must expect to be notified multiple times
 * for the same parameter and declaration.
 *
 * @see com.github.joschi.jadconfig.JadConfig#addParameterListener(ParameterListener)
 * @see ParameterInfoCollector
 */
public interface ParameterListener {

    /**
     * @param declaration The declaration of the parameter in the configuration bean, including where its value has
     *                    been read from. The parameter name is available via {@link ParameterMetadata#name()}.
     * @param rawValue    The raw (trimmed if enabled) value read from the repository, or {@code null} if no repository
     *                    provided a value. In contrast to {@link ParameterDeclaration#value()} this is passed on even
     *                    for sensitive parameters.
     */
    void onParameterProcessed(ParameterDeclaration declaration, @Nullable String rawValue);
}
