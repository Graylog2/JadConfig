package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for converting a comma-separated list to {@code Set<String>}
 *
 * @author jschalanda
 */
public class StringSetConverter implements Converter<Set<String>> {

    protected static final String DELIMITER = ",";

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

        if ("".equals(value)) {

            return Collections.emptySet();
        }

        final Set<String> result = new HashSet<>();
        Collections.addAll(result, value.split(DELIMITER));

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     * <p/>
     * The elements of the provided {@link Set} of {@link String}s are concatenated with commas.
     *
     * @param value The configuration parameter's {@link Set} of {@link String} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Set<String> value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        StringBuilder sb = new StringBuilder();
        String separator = "";

        for (String element : value) {
            sb.append(separator);
            sb.append(element);
            separator = DELIMITER;
        }

        return sb.toString();
    }
}
