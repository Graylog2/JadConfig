package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.net.URI;

/**
 * Converter for type {@link URI}
 *
 * @author jschalanda
 */
public class URIConverter implements Converter<URI> {

    @Override
    public URI convert(String value) {

        URI result;

        try {
            result = new URI(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to URI.", ex);
        }

        return result;
    }
}
