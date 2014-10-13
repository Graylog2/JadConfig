package com.github.joschi.jadconfig.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SizeTest {
    @Test
    public void convertsToPetabytes() throws Exception {
        assertEquals(2, Size.petabytes(2).toPetabytes());
    }

    @Test
    public void convertsToTerabytes() throws Exception {
        assertEquals(2, Size.terabytes(2).toTerabytes());
    }

    @Test
    public void convertsToGigabytes() throws Exception {
        assertEquals(2048, Size.terabytes(2).toGigabytes());
    }

    @Test
    public void convertsToMegabytes() throws Exception {
        assertEquals(2048, Size.gigabytes(2).toMegabytes());
    }

    @Test
    public void convertsToKilobytes() throws Exception {
        assertEquals(2048, Size.megabytes(2).toKilobytes());
    }

    @Test
    public void convertsToBytes() throws Exception {
        assertEquals(2048L, Size.kilobytes(2).toBytes());
    }

    @Test
    public void parsesPetabytes() throws Exception {
        assertEquals(Size.petabytes(2), Size.parse("2PB"));
        assertEquals(Size.petabytes(2), Size.parse("2PiB"));
        assertEquals(Size.petabytes(1), Size.parse("1 petabyte"));
        assertEquals(Size.petabytes(2), Size.parse("2 petabytes"));
    }


    @Test
    public void parsesTerabytes() throws Exception {
        assertEquals(Size.terabytes(2), Size.parse("2TB"));
        assertEquals(Size.terabytes(2), Size.parse("2TiB"));
        assertEquals(Size.terabytes(1), Size.parse("1 terabyte"));
        assertEquals(Size.terabytes(2), Size.parse("2 terabytes"));
    }

    @Test
    public void parsesGigabytes() throws Exception {
        assertEquals(Size.gigabytes(2), Size.parse("2GB"));
        assertEquals(Size.gigabytes(2), Size.parse("2GiB"));
        assertEquals(Size.gigabytes(1), Size.parse("1 gigabyte"));
        assertEquals(Size.gigabytes(2), Size.parse("2 gigabytes"));
    }

    @Test
    public void parsesMegabytes() throws Exception {
        assertEquals(Size.megabytes(2), Size.parse("2MB"));
        assertEquals(Size.megabytes(2), Size.parse("2MiB"));
        assertEquals(Size.megabytes(1), Size.parse("1 megabyte"));
        assertEquals(Size.megabytes(2), Size.parse("2 megabytes"));
    }

    @Test
    public void parsesKilobytes() throws Exception {
        assertEquals(Size.kilobytes(2), Size.parse("2KB"));
        assertEquals(Size.kilobytes(2), Size.parse("2KiB"));
        assertEquals(Size.kilobytes(1), Size.parse("1 kilobyte"));
        assertEquals(Size.kilobytes(2), Size.parse("2 kilobytes"));
    }

    @Test
    public void parsesBytes() throws Exception {
        assertEquals(Size.bytes(2), Size.parse("2B"));
        assertEquals(Size.bytes(1), Size.parse("1 byte"));
        assertEquals(Size.bytes(2), Size.parse("2 bytes"));
    }

    @Test
    public void parseSizeWithWhiteSpaces() {
        assertEquals(Size.kilobytes(64), Size.parse("64   kilobytes"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongSizeCount() {
        Size.parse("three bytes");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongSizeUnit() {
        Size.parse("1EB");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongSizeFormat() {
        Size.parse("1 mega byte");
    }

    @Test
    public void isHumanReadable() throws Exception {
        assertEquals("3 gigabytes", Size.gigabytes(3).toString());
        assertEquals("1 kilobyte", Size.kilobytes(1).toString());
    }

    @Test
    public void hasAQuantity() throws Exception {
        assertEquals(3, Size.gigabytes(3).getQuantity());
    }

    @Test
    public void hasAUnit() throws Exception {
        assertEquals(SizeUnit.GIGABYTES, Size.gigabytes(3).getUnit());
    }
}
