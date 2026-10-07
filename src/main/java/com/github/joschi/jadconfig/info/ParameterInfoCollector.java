package com.github.joschi.jadconfig.info;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link ParameterListener} collecting {@link ParameterInfo} for all processed parameters, keyed by parameter name.
 * <p>
 * If a configuration bean is processed multiple times, the latest value and source of its declarations win and its
 * declarations are not duplicated. Default values are not affected by repeated processing.
 * <p>
 * This class is not thread-safe.
 */
public class ParameterInfoCollector implements ParameterListener {

    private final Map<String, Map<DeclarationKey, ParameterDeclaration>> declarationsByName = new LinkedHashMap<>();

    @Override
    public void onParameterProcessed(ParameterDeclaration declaration, @Nullable String rawValue) {
        // Reprocessing a bean must not add its declarations twice
        declarationsByName.computeIfAbsent(declaration.metadata().name(), k -> new LinkedHashMap<>())
                .put(DeclarationKey.of(declaration), declaration);
    }

    /**
     * Returns an immutable snapshot of the information collected so far, keyed by parameter name.
     */
    public Map<String, ParameterInfo> getParameterInfos() {
        final Map<String, ParameterInfo> result = new LinkedHashMap<>();
        declarationsByName.forEach((name, declarations) ->
                result.put(name, new ParameterInfo(name, new ArrayList<>(declarations.values()))));
        return Collections.unmodifiableMap(result);
    }

    /**
     * Identifies a field in a configuration bean class. The declaring class is required to tell apart a field from a
     * field with the same name in a superclass which it hides.
     */
    private record DeclarationKey(Class<?> beanClass, Class<?> declaringClass, String fieldName) {
        static DeclarationKey of(ParameterDeclaration declaration) {
            return new DeclarationKey(declaration.beanClass(), declaration.declaringClass(), declaration.metadata().fieldName());
        }
    }
}
