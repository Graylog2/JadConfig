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

    @Override
    public File convertFrom(String value) {

        if (value == null) {

            throw new ParameterException("Couldn convert value \"" + value + "\" to File object.");
        }

        return new File(value);
    }

    @Override
    public String convertTo(File value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.getPath();
    }
}
