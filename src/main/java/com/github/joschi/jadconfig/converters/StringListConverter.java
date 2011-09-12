package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class for converting a comma-separated list to {@code List<String>}
 *
 * @author jschalanda
 */
public class StringListConverter implements Converter<List<String>> {

    private static final String DELIMITER = ",";

    @Override
    public List<String> convertFrom(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to list of String.");
        }

        if ("".equals(value)) {

            return Collections.emptyList();
        }

        return Arrays.asList(value.split(DELIMITER));
    }

    @Override
    public String convertTo(List<String> value) {

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
