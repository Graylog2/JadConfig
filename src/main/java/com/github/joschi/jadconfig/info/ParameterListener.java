package com.github.joschi.jadconfig.info;

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
     * @param name        The parameter name
     * @param declaration The declaration of the parameter in the configuration bean
     * @param source      Where the value has been read from, or {@code null} if no repository provided a value and
     *                    the default value is being used
     * @param value       The raw (trimmed) value read from the repository, or {@code null} if no repository provided
     *                    a value. This is passed on even for sensitive parameters.
     */
    void onParameterProcessed(String name, ParameterDeclaration declaration, ParameterSource source, String value);
}
