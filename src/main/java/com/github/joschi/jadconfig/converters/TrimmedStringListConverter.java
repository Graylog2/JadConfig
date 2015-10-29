package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for converting a comma-separated list to a {@code List<String>} of trimmed strings.
 *
 * @author jschalanda
 */
public class TrimmedStringListConverter extends StringListConverter {
    /**
     * Returns a {@link List} of {@link String}s representing the specified {@link String} {@literal value}.
     * <p/>
     * The {@literal value} is being split on comma.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link List} of {@link String}s representing the configuration parameter's value
     */
    @Override
    public List<String> convertFrom(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to list of String.");
        }

        if (value.isEmpty()) {
            return Collections.emptyList();
        }

        final String[] values = value.split(DELIMITER);
        final List<String> result = new ArrayList<>(value.length());
        for (String s : values) {
            result.add(s.trim());
        }

        return result;
    }
}
