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
    public List<String> convert(String value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to list of String.");
        }

        if ("".equals(value)) {

            return Collections.emptyList();
        }

        return Arrays.asList(value.split(DELIMITER));
    }
}
