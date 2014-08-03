package com.github.joschi.jadconfig;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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