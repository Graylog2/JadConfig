package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.ParameterException;
import com.github.joschi.jadconfig.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link UUIDConverter}
 *
 * @author jschalanda
 */
public class UUIDConverterTest {

    private UUIDConverter converter;

    @BeforeEach
    public void setUp() {

        converter = new UUIDConverter();
    }

    @Test
    public void testConvertFrom() {

        UUID uuid = UUID.randomUUID();

        Assertions.assertEquals(uuid, converter.convertFrom(uuid.toString()));
    }

    @Test
    public void testConvertFromNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom(null)
        );
    }

    @Test
    public void testConvertFromEmptyString() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("")
        );
    }

    @Test
    public void testConvertFromInvalid() {
        assertThrows(ParameterException.class,
                () -> converter.convertFrom("Invalid UUID")
        );
    }

    @Test
    public void testConvertTo() {
        UUID uuid = UUID.randomUUID();

        Assertions.assertEquals(uuid.toString(), converter.convertTo(uuid));
    }

    @Test
    public void testConvertToNull() {
        assertThrows(ParameterException.class,
                () -> converter.convertTo(null)
        );
    }
}
