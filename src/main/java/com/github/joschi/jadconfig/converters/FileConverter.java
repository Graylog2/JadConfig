package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.io.File;

/**
 * Class to convertFrom a given file name to a {@link File} object
 *
 * @author jschalanda
 */
public class FileConverter implements Converter<File> {

    /**
     * Returns a {@link File} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link File} instance representing the configuration parameter's value
     */
    @Override
    public File convertFrom(String value) {

        if (value == null) {

            throw new ParameterException("Couldn convert value \"" + value + "\" to File object.");
        }

        return new File(value);
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link File} representation
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(File value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getPath();
    }
}
