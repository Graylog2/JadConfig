package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to convertFrom a given file name to a {@link File} object
 *
 * @author jschalanda
 */
public class PathConverter implements Converter<Path> {

    /**
     * Returns a {@link Path} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link Path} instance representing the configuration parameter's value
     */
    @Override
    public Path convertFrom(String value) {

        if (value == null) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to File object.");
        }

        return Paths.get(value);
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link Path} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(Path value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
