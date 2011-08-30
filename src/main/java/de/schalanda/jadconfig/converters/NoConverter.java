package de.schalanda.jadconfig.converters;

import de.schalanda.jadconfig.Converter;

/**
 * No-operation converter being used as default for {@link de.schalanda.jadconfig.Parameter#converter()}.
 * Always throws an {@link UnsupportedOperationException}
 *
 * @author jschalanda
 */
public class NoConverter implements Converter<String> {

    @Override
    public String convert(String value) {
        throw new UnsupportedOperationException();
    }
}
