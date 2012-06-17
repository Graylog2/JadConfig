package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

import java.util.UUID;

/**
 * Converter for type {@link UUID}
 *
 * @author jschalanda
 */
public class UUIDConverter implements Converter<UUID> {

    /**
     * Returns a {@link UUID} instance representing the specified {@link String} {@literal value}.
     *
     * @param value The configuration parameter's {@link String} value
     * @return A {@link UUID} instance representing the configuration parameter's value
     */
    @Override
    public UUID convertFrom(String value) {

        UUID result;

        try {
            result = UUID.fromString(value);
        } catch (Exception ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to UUID.", ex);
        }

        return result;
    }

    /**
     * Returns a {@link String} instance representing the configuration parameter's {@literal value}.
     *
     * @param value The configuration parameter's {@link UUID} value
     * @return A {@link String} instance representing the configuration parameter's typed value
     */
    @Override
    public String convertTo(UUID value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
