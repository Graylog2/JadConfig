package com.github.joschi.jadconfig;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TypeReferenceTest {

    @Test
    public void testNewInstance() throws Exception {
        assertEquals("", new TypeReference<String>() {
        }.newInstance());
        assertEquals(Collections.<String>emptyList(), new TypeReference<ArrayList<String>>() {
        }.newInstance());
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(String.class, new TypeReference<String>() {
        }.getType());
        assertEquals("java.util.List<java.lang.String>", new TypeReference<List<String>>() {
        }.getType().toString());
    }

    @Test
    public void testCompareTo() throws Exception {
        final TypeReference<Object> typeReference = new TypeReference<Object>() {
        };

        assertEquals(0, typeReference.compareTo(null));
        assertEquals(0, typeReference.compareTo(typeReference));
        assertEquals(0, typeReference.compareTo(new TypeReference<Object>() {
        }));
    }

    @Test
    public void testEquals() throws Exception {
        final TypeReference<Object> typeReference = new TypeReference<Object>() {
        };

        assertTrue(typeReference.equals(typeReference));
        assertTrue(typeReference.equals(new TypeReference<Object>() {
        }));
        assertFalse(typeReference.equals((TypeReference<Object>) null));
        assertFalse(typeReference.equals(new Object()));
        assertFalse(typeReference.equals(new TypeReference<String>() {
        }));
    }

    @Test
    public void testHashCode() throws Exception {
        final TypeReference<Object> typeReference = new TypeReference<Object>() {
        };

        assertEquals(typeReference.hashCode(), typeReference.hashCode());
        assertEquals(new TypeReference<Object>() {
        }.hashCode(), typeReference.hashCode());
        assertNotEquals(new TypeReference<String>() {
        }.hashCode(), typeReference.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(
                "TypeReference{class java.lang.Object}",
                new TypeReference<Object>() {
                }.toString());
        assertEquals(
                "TypeReference{java.util.List<java.lang.String>}",
                new TypeReference<List<String>>() {
                }.toString());
    }
}