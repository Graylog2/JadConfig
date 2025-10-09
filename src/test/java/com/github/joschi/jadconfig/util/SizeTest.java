package com.github.joschi.jadconfig.util;

import com.github.joschi.jadconfig.ParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(Size.petabytes(2), Size.parse("2p"));
        assertEquals(Size.petabytes(2), Size.parse("2P"));
        assertEquals(Size.petabytes(2), Size.parse("2pb"));
        assertEquals(Size.petabytes(2), Size.parse("2PB"));
        assertEquals(Size.petabytes(2), Size.parse("2PiB"));
        assertEquals(Size.petabytes(1), Size.parse("1 petabyte"));
        assertEquals(Size.petabytes(2), Size.parse("2 petabytes"));
    }


    @Test
    public void parsesTerabytes() throws Exception {
        assertEquals(Size.terabytes(2), Size.parse("2t"));
        assertEquals(Size.terabytes(2), Size.parse("2T"));
        assertEquals(Size.terabytes(2), Size.parse("2tb"));
        assertEquals(Size.terabytes(2), Size.parse("2TB"));
        assertEquals(Size.terabytes(2), Size.parse("2TiB"));
        assertEquals(Size.terabytes(1), Size.parse("1 terabyte"));
        assertEquals(Size.terabytes(2), Size.parse("2 terabytes"));
    }

    @Test
    public void parsesGigabytes() throws Exception {
        assertEquals(Size.gigabytes(2), Size.parse("2g"));
        assertEquals(Size.gigabytes(2), Size.parse("2G"));
        assertEquals(Size.gigabytes(2), Size.parse("2gb"));
        assertEquals(Size.gigabytes(2), Size.parse("2GB"));
        assertEquals(Size.gigabytes(2), Size.parse("2GiB"));
        assertEquals(Size.gigabytes(1), Size.parse("1 gigabyte"));
        assertEquals(Size.gigabytes(2), Size.parse("2 gigabytes"));
    }

    @Test
    public void parsesMegabytes() throws Exception {
        assertEquals(Size.megabytes(2), Size.parse("2m"));
        assertEquals(Size.megabytes(2), Size.parse("2M"));
        assertEquals(Size.megabytes(2), Size.parse("2mb"));
        assertEquals(Size.megabytes(2), Size.parse("2MB"));
        assertEquals(Size.megabytes(2), Size.parse("2MiB"));
        assertEquals(Size.megabytes(1), Size.parse("1 megabyte"));
        assertEquals(Size.megabytes(2), Size.parse("2 megabytes"));
    }

    @Test
    public void parsesKilobytes() throws Exception {
        assertEquals(Size.kilobytes(2), Size.parse("2k"));
        assertEquals(Size.kilobytes(2), Size.parse("2K"));
        assertEquals(Size.kilobytes(2), Size.parse("2kb"));
        assertEquals(Size.kilobytes(2), Size.parse("2KB"));
        assertEquals(Size.kilobytes(2), Size.parse("2KiB"));
        assertEquals(Size.kilobytes(1), Size.parse("1 kilobyte"));
        assertEquals(Size.kilobytes(2), Size.parse("2 kilobytes"));
    }

    @Test
    public void parsesBytes() throws Exception {
        assertEquals(Size.bytes(2), Size.parse("2b"));
        assertEquals(Size.bytes(2), Size.parse("2B"));
        assertEquals(Size.bytes(1), Size.parse("1 byte"));
        assertEquals(Size.bytes(2), Size.parse("2 bytes"));
    }

    @Test
    public void parseSizeWithWhiteSpaces() {
        assertEquals(Size.kilobytes(64), Size.parse("64   kilobytes"));
    }

    @Test
    public void unableParseWrongSizeCount() {
        assertThrows(IllegalArgumentException.class,
                () -> Size.parse("three bytes")
        );
    }

    @Test
    public void unableParseWrongSizeUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> Size.parse("1EB")
        );
    }

    @Test
    public void unableParseWrongSizeFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> Size.parse("1 mega byte")
        );
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

    @Test
    public void isComparable() throws Exception {
        // both zero
        assertEquals(0, Size.bytes(0).compareTo(Size.bytes(0)));
        assertEquals(0, Size.bytes(0).compareTo(Size.kilobytes(0)));
        assertEquals(0, Size.bytes(0).compareTo(Size.megabytes(0)));
        assertEquals(0, Size.bytes(0).compareTo(Size.gigabytes(0)));
        assertEquals(0, Size.bytes(0).compareTo(Size.terabytes(0)));
        assertEquals(0, Size.bytes(0).compareTo(Size.petabytes(0)));

        assertEquals(0, Size.kilobytes(0).compareTo(Size.bytes(0)));
        assertEquals(0, Size.kilobytes(0).compareTo(Size.kilobytes(0)));
        assertEquals(0, Size.kilobytes(0).compareTo(Size.megabytes(0)));
        assertEquals(0, Size.kilobytes(0).compareTo(Size.gigabytes(0)));
        assertEquals(0, Size.kilobytes(0).compareTo(Size.terabytes(0)));
        assertEquals(0, Size.kilobytes(0).compareTo(Size.petabytes(0)));

        assertEquals(0, Size.megabytes(0).compareTo(Size.bytes(0)));
        assertEquals(0, Size.megabytes(0).compareTo(Size.kilobytes(0)));
        assertEquals(0, Size.megabytes(0).compareTo(Size.megabytes(0)));
        assertEquals(0, Size.megabytes(0).compareTo(Size.gigabytes(0)));
        assertEquals(0, Size.megabytes(0).compareTo(Size.terabytes(0)));
        assertEquals(0, Size.megabytes(0).compareTo(Size.petabytes(0)));

        assertEquals(0, Size.gigabytes(0).compareTo(Size.bytes(0)));
        assertEquals(0, Size.gigabytes(0).compareTo(Size.kilobytes(0)));
        assertEquals(0, Size.gigabytes(0).compareTo(Size.megabytes(0)));
        assertEquals(0, Size.gigabytes(0).compareTo(Size.gigabytes(0)));
        assertEquals(0, Size.gigabytes(0).compareTo(Size.terabytes(0)));
        assertEquals(0, Size.gigabytes(0).compareTo(Size.petabytes(0)));

        assertEquals(0, Size.terabytes(0).compareTo(Size.bytes(0)));
        assertEquals(0, Size.terabytes(0).compareTo(Size.kilobytes(0)));
        assertEquals(0, Size.terabytes(0).compareTo(Size.megabytes(0)));
        assertEquals(0, Size.terabytes(0).compareTo(Size.gigabytes(0)));
        assertEquals(0, Size.terabytes(0).compareTo(Size.terabytes(0)));
        assertEquals(0, Size.terabytes(0).compareTo(Size.petabytes(0)));

        assertEquals(0, Size.petabytes(0).compareTo(Size.bytes(0)));
        assertEquals(0, Size.petabytes(0).compareTo(Size.kilobytes(0)));
        assertEquals(0, Size.petabytes(0).compareTo(Size.megabytes(0)));
        assertEquals(0, Size.petabytes(0).compareTo(Size.gigabytes(0)));
        assertEquals(0, Size.petabytes(0).compareTo(Size.terabytes(0)));
        assertEquals(0, Size.petabytes(0).compareTo(Size.petabytes(0)));

        // one zero, one negative
        assertTrue(Size.bytes(0).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.bytes(0).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.bytes(0).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.bytes(0).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.bytes(0).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.bytes(0).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.kilobytes(0).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.megabytes(0).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.megabytes(0).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.megabytes(0).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.megabytes(0).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.megabytes(0).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.megabytes(0).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.gigabytes(0).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.terabytes(0).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.terabytes(0).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.terabytes(0).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.terabytes(0).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.terabytes(0).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.terabytes(0).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.petabytes(0).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.petabytes(0).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.petabytes(0).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.petabytes(0).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.petabytes(0).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.petabytes(0).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.bytes(-1).compareTo(Size.bytes(0)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.kilobytes(0)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.megabytes(0)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.gigabytes(0)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.terabytes(0)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.petabytes(0)) < 0);

        assertTrue(Size.kilobytes(-1).compareTo(Size.bytes(0)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.kilobytes(0)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.megabytes(0)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.gigabytes(0)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.terabytes(0)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.petabytes(0)) < 0);

        assertTrue(Size.megabytes(-1).compareTo(Size.bytes(0)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.kilobytes(0)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.megabytes(0)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.gigabytes(0)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.terabytes(0)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.petabytes(0)) < 0);

        assertTrue(Size.gigabytes(-1).compareTo(Size.bytes(0)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.kilobytes(0)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.megabytes(0)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.gigabytes(0)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.terabytes(0)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.petabytes(0)) < 0);

        assertTrue(Size.terabytes(-1).compareTo(Size.bytes(0)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.kilobytes(0)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.megabytes(0)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.gigabytes(0)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.terabytes(0)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.petabytes(0)) < 0);

        assertTrue(Size.petabytes(-1).compareTo(Size.bytes(0)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.kilobytes(0)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.megabytes(0)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.gigabytes(0)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.terabytes(0)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.petabytes(0)) < 0);

        // one zero, one positive
        assertTrue(Size.bytes(0).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.bytes(0).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.bytes(0).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.bytes(0).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.bytes(0).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.bytes(0).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.kilobytes(0).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.kilobytes(0).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.megabytes(0).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.megabytes(0).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.megabytes(0).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.megabytes(0).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.megabytes(0).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.megabytes(0).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.gigabytes(0).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.gigabytes(0).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.terabytes(0).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.terabytes(0).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.terabytes(0).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.terabytes(0).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.terabytes(0).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.terabytes(0).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.petabytes(0).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.petabytes(0).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.petabytes(0).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.petabytes(0).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.petabytes(0).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.petabytes(0).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.bytes(1).compareTo(Size.bytes(0)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.kilobytes(0)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.megabytes(0)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.gigabytes(0)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.terabytes(0)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.petabytes(0)) > 0);

        assertTrue(Size.kilobytes(1).compareTo(Size.bytes(0)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.kilobytes(0)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.megabytes(0)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.gigabytes(0)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.petabytes(0)) > 0);

        assertTrue(Size.megabytes(1).compareTo(Size.bytes(0)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.kilobytes(0)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.megabytes(0)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.gigabytes(0)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.petabytes(0)) > 0);

        assertTrue(Size.gigabytes(1).compareTo(Size.bytes(0)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.kilobytes(0)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.megabytes(0)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.gigabytes(0)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.terabytes(0)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.petabytes(0)) > 0);

        assertTrue(Size.terabytes(1).compareTo(Size.bytes(0)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.kilobytes(0)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.megabytes(0)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.gigabytes(0)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.terabytes(0)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.petabytes(0)) > 0);

        assertTrue(Size.petabytes(1).compareTo(Size.bytes(0)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.kilobytes(0)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.megabytes(0)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.gigabytes(0)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.terabytes(0)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.petabytes(0)) > 0);

        // both negative
        assertTrue(Size.bytes(-2).compareTo(Size.bytes(-1)) < 0);
        assertTrue(Size.bytes(-2).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.bytes(-2).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.bytes(-2).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.bytes(-2).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.bytes(-2).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.kilobytes(-2).compareTo(Size.bytes(-1)) < 0);
        assertTrue(Size.kilobytes(-2).compareTo(Size.kilobytes(-1)) < 0);
        assertTrue(Size.kilobytes(-2).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.kilobytes(-2).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.kilobytes(-2).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.kilobytes(-2).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.megabytes(-2).compareTo(Size.bytes(-1)) < 0);
        assertTrue(Size.megabytes(-2).compareTo(Size.kilobytes(-1)) < 0);
        assertTrue(Size.megabytes(-2).compareTo(Size.megabytes(-1)) < 0);
        assertTrue(Size.megabytes(-2).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.megabytes(-2).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.megabytes(-2).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.gigabytes(-2).compareTo(Size.bytes(-1)) < 0);
        assertTrue(Size.gigabytes(-2).compareTo(Size.kilobytes(-1)) < 0);
        assertTrue(Size.gigabytes(-2).compareTo(Size.megabytes(-1)) < 0);
        assertTrue(Size.gigabytes(-2).compareTo(Size.gigabytes(-1)) < 0);
        assertTrue(Size.gigabytes(-2).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.gigabytes(-2).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.terabytes(-2).compareTo(Size.bytes(-1)) < 0);
        assertTrue(Size.terabytes(-2).compareTo(Size.kilobytes(-1)) < 0);
        assertTrue(Size.terabytes(-2).compareTo(Size.megabytes(-1)) < 0);
        assertTrue(Size.terabytes(-2).compareTo(Size.gigabytes(-1)) < 0);
        assertTrue(Size.terabytes(-2).compareTo(Size.terabytes(-1)) < 0);
        assertTrue(Size.terabytes(-2).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.petabytes(-2).compareTo(Size.bytes(-1)) < 0);
        assertTrue(Size.petabytes(-2).compareTo(Size.kilobytes(-1)) < 0);
        assertTrue(Size.petabytes(-2).compareTo(Size.megabytes(-1)) < 0);
        assertTrue(Size.petabytes(-2).compareTo(Size.gigabytes(-1)) < 0);
        assertTrue(Size.petabytes(-2).compareTo(Size.terabytes(-1)) < 0);
        assertTrue(Size.petabytes(-2).compareTo(Size.petabytes(-1)) < 0);

        assertTrue(Size.bytes(-1).compareTo(Size.bytes(-2)) > 0);
        assertTrue(Size.bytes(-1).compareTo(Size.kilobytes(-2)) > 0);
        assertTrue(Size.bytes(-1).compareTo(Size.megabytes(-2)) > 0);
        assertTrue(Size.bytes(-1).compareTo(Size.gigabytes(-2)) > 0);
        assertTrue(Size.bytes(-1).compareTo(Size.terabytes(-2)) > 0);
        assertTrue(Size.bytes(-1).compareTo(Size.petabytes(-2)) > 0);

        assertTrue(Size.kilobytes(-1).compareTo(Size.bytes(-2)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.kilobytes(-2)) > 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.megabytes(-2)) > 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.gigabytes(-2)) > 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.terabytes(-2)) > 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.petabytes(-2)) > 0);

        assertTrue(Size.megabytes(-1).compareTo(Size.bytes(-2)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.kilobytes(-2)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.megabytes(-2)) > 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.gigabytes(-2)) > 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.terabytes(-2)) > 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.petabytes(-2)) > 0);

        assertTrue(Size.gigabytes(-1).compareTo(Size.bytes(-2)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.kilobytes(-2)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.megabytes(-2)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.gigabytes(-2)) > 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.terabytes(-2)) > 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.petabytes(-2)) > 0);

        assertTrue(Size.terabytes(-1).compareTo(Size.bytes(-2)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.kilobytes(-2)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.megabytes(-2)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.gigabytes(-2)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.terabytes(-2)) > 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.petabytes(-2)) > 0);

        assertTrue(Size.petabytes(-1).compareTo(Size.bytes(-2)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.kilobytes(-2)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.megabytes(-2)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.gigabytes(-2)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.terabytes(-2)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.petabytes(-2)) > 0);

        // both positive
        assertTrue(Size.bytes(1).compareTo(Size.bytes((2))) < 0);
        assertTrue(Size.bytes(1).compareTo(Size.kilobytes((2))) < 0);
        assertTrue(Size.bytes(1).compareTo(Size.megabytes((2))) < 0);
        assertTrue(Size.bytes(1).compareTo(Size.gigabytes((2))) < 0);
        assertTrue(Size.bytes(1).compareTo(Size.terabytes((2))) < 0);
        assertTrue(Size.bytes(1).compareTo(Size.petabytes((2))) < 0);

        assertTrue(Size.kilobytes(1).compareTo(Size.bytes((2))) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.kilobytes((2))) < 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.megabytes((2))) < 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.gigabytes((2))) < 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.terabytes((2))) < 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.petabytes((2))) < 0);

        assertTrue(Size.megabytes(1).compareTo(Size.bytes((2))) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.kilobytes((2))) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.megabytes((2))) < 0);
        assertTrue(Size.megabytes(1).compareTo(Size.gigabytes((2))) < 0);
        assertTrue(Size.megabytes(1).compareTo(Size.terabytes((2))) < 0);
        assertTrue(Size.megabytes(1).compareTo(Size.petabytes((2))) < 0);

        assertTrue(Size.gigabytes(1).compareTo(Size.bytes((2))) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.kilobytes((2))) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.megabytes((2))) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.gigabytes((2))) < 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.terabytes((2))) < 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.petabytes((2))) < 0);

        assertTrue(Size.terabytes(1).compareTo(Size.bytes((2))) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.kilobytes((2))) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.megabytes((2))) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.gigabytes((2))) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.terabytes((2))) < 0);
        assertTrue(Size.terabytes(1).compareTo(Size.petabytes((2))) < 0);

        assertTrue(Size.petabytes(1).compareTo(Size.bytes((2))) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.kilobytes((2))) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.megabytes((2))) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.gigabytes((2))) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.terabytes((2))) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.petabytes((2))) < 0);

        assertTrue(Size.bytes(2).compareTo(Size.bytes((1))) > 0);
        assertTrue(Size.bytes(2).compareTo(Size.kilobytes((1))) < 0);
        assertTrue(Size.bytes(2).compareTo(Size.megabytes((1))) < 0);
        assertTrue(Size.bytes(2).compareTo(Size.gigabytes((1))) < 0);
        assertTrue(Size.bytes(2).compareTo(Size.terabytes((1))) < 0);
        assertTrue(Size.bytes(2).compareTo(Size.petabytes((1))) < 0);

        assertTrue(Size.kilobytes(2).compareTo(Size.bytes((1))) > 0);
        assertTrue(Size.kilobytes(2).compareTo(Size.kilobytes((1))) > 0);
        assertTrue(Size.kilobytes(2).compareTo(Size.megabytes((1))) < 0);
        assertTrue(Size.kilobytes(2).compareTo(Size.gigabytes((1))) < 0);
        assertTrue(Size.kilobytes(2).compareTo(Size.terabytes((1))) < 0);
        assertTrue(Size.kilobytes(2).compareTo(Size.petabytes((1))) < 0);

        assertTrue(Size.megabytes(2).compareTo(Size.bytes((1))) > 0);
        assertTrue(Size.megabytes(2).compareTo(Size.kilobytes((1))) > 0);
        assertTrue(Size.megabytes(2).compareTo(Size.megabytes((1))) > 0);
        assertTrue(Size.megabytes(2).compareTo(Size.gigabytes((1))) < 0);
        assertTrue(Size.megabytes(2).compareTo(Size.terabytes((1))) < 0);
        assertTrue(Size.megabytes(2).compareTo(Size.petabytes((1))) < 0);

        assertTrue(Size.gigabytes(2).compareTo(Size.bytes((1))) > 0);
        assertTrue(Size.gigabytes(2).compareTo(Size.kilobytes((1))) > 0);
        assertTrue(Size.gigabytes(2).compareTo(Size.megabytes((1))) > 0);
        assertTrue(Size.gigabytes(2).compareTo(Size.gigabytes((1))) > 0);
        assertTrue(Size.gigabytes(2).compareTo(Size.terabytes((1))) < 0);
        assertTrue(Size.gigabytes(2).compareTo(Size.petabytes((1))) < 0);

        assertTrue(Size.terabytes(2).compareTo(Size.bytes((1))) > 0);
        assertTrue(Size.terabytes(2).compareTo(Size.kilobytes((1))) > 0);
        assertTrue(Size.terabytes(2).compareTo(Size.megabytes((1))) > 0);
        assertTrue(Size.terabytes(2).compareTo(Size.gigabytes((1))) > 0);
        assertTrue(Size.terabytes(2).compareTo(Size.terabytes((1))) > 0);
        assertTrue(Size.terabytes(2).compareTo(Size.petabytes((1))) < 0);

        assertTrue(Size.petabytes(2).compareTo(Size.bytes((1))) > 0);
        assertTrue(Size.petabytes(2).compareTo(Size.kilobytes((1))) > 0);
        assertTrue(Size.petabytes(2).compareTo(Size.megabytes((1))) > 0);
        assertTrue(Size.petabytes(2).compareTo(Size.gigabytes((1))) > 0);
        assertTrue(Size.petabytes(2).compareTo(Size.terabytes((1))) > 0);
        assertTrue(Size.petabytes(2).compareTo(Size.petabytes((1))) > 0);

        // one negative, one positive
        assertTrue(Size.bytes(-1).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.bytes(-1).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.kilobytes(-1).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.kilobytes(-1).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.megabytes(-1).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.megabytes(-1).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.gigabytes(-1).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.gigabytes(-1).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.terabytes(-1).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.terabytes(-1).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.petabytes(-1).compareTo(Size.bytes(1)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.kilobytes(1)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.megabytes(1)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.gigabytes(1)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.terabytes(1)) < 0);
        assertTrue(Size.petabytes(-1).compareTo(Size.petabytes(1)) < 0);

        assertTrue(Size.bytes(1).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.bytes(1).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.kilobytes(1).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.kilobytes(1).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.megabytes(1).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.megabytes(1).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.gigabytes(1).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.gigabytes(1).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.terabytes(1).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.terabytes(1).compareTo(Size.petabytes(-1)) > 0);

        assertTrue(Size.petabytes(1).compareTo(Size.bytes(-1)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.kilobytes(-1)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.megabytes(-1)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.gigabytes(-1)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.terabytes(-1)) > 0);
        assertTrue(Size.petabytes(1).compareTo(Size.petabytes(-1)) > 0);
    }
}
