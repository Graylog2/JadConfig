package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class for converting a comma-separated list to a {@code SortedSet<String>} of trimmed strings.
 *
 * @author jschalanda
 */
public class TrimmedStringSortedSetConverter extends StringSortedSetConverter {
    /**
     * Returns a {@link SortedSet} of {@link String}s representing the specified {@link String} {@literal value}.
     * <p/>
     * The {@literal value} is being split on comma.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link SortedSet} of {@link String}s representing the configuration parameter's value
     */
    @Override
    public SortedSet<String> convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to set of String.");
        }

        if (value.isEmpty()) {
            return new TreeSet<>();
        }

        final String[] values = value.split(DELIMITER);
        final SortedSet<String> result = new TreeSet<>();
        for (String s : values) {
            result.add(s.trim());
        }

        return result;
    }
}
