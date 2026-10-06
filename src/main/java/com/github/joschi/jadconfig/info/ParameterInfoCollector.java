package com.github.joschi.jadconfig.info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link ParameterListener} collecting {@link ParameterInfo} for all processed parameters, keyed by parameter name.
 * <p>
 * If a configuration bean is processed multiple times, the latest value and source win and its declarations are
 * not duplicated. Default values are not affected by repeated processing.
 * <p>
 * This class is not thread-safe.
 */
public class ParameterInfoCollector implements ParameterListener {

    private final Map<String, Entry> entries = new LinkedHashMap<>();

    @Override
    public void onParameterProcessed(String name, ParameterDeclaration declaration, ParameterSource source, String value) {
        final Entry entry = entries.computeIfAbsent(name, k -> new Entry());
        entry.source = source;
        entry.value = value;
        // Reprocessing a bean must not add its declarations twice
        entry.declarations.put(declaration.beanClass().getName() + "#" + declaration.metadata().fieldName(), declaration);
    }

    /**
     * Returns an immutable snapshot of the information collected so far, keyed by parameter name.
     */
    public Map<String, ParameterInfo> getParameterInfos() {
        final Map<String, ParameterInfo> result = new LinkedHashMap<>();
        entries.forEach((name, entry) ->
                result.put(name, new ParameterInfo(name, entry.source, entry.value, new ArrayList<>(entry.declarations.values()))));
        return Collections.unmodifiableMap(result);
    }

    private static final class Entry {
        private ParameterSource source;
        private String value;
        private final Map<String, ParameterDeclaration> declarations = new LinkedHashMap<>();
    }
}
