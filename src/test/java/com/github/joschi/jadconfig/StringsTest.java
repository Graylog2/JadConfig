package com.github.joschi.jadconfig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringsTest {

    @Test
    public void trimWorksWithNull() throws Exception {
        assertNull(Strings.trim(null));
    }

    @Test
    public void trimRemovesWhitespaces() {
        assertEquals("string", Strings.trim(" string\t"));
    }
}