package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for converting a comma-separated list to a {@code Set<String>} of trimmed strings.
 *
 * @author jschalanda
 */
public class TrimmedStringSetConverter extends StringSetConverter {
    /**
     * Returns a {@link Set} of {@link String}s representing the specified {@link String} {@literal value}.
     * <p/>
     * The {@literal value} is being split on comma.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Set} of {@link String}s representing the configuration parameter's value
     */
    @Override
    public Set<String> convertFrom(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to set of String.");
        }

        if (value.isEmpty()) {
            return Collections.emptySet();
        }

        final String[] values = value.split(DELIMITER);
        final Set<String> result = new HashSet<>(value.length());
        for (String s : values) {
            result.add(s.trim());
        }

        return result;
    }
}
