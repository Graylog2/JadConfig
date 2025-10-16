package com.github.joschi.jadconfig.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeUnitTest {
    // BYTES

    @Test
    public void oneByteInBytes() throws Exception {
        assertEquals(1, SizeUnit.BYTES.convert(1, SizeUnit.BYTES));
        assertEquals(1, SizeUnit.BYTES.toBytes(1));
    }


    @Test
    public void oneByteInKilobytes() throws Exception {
        assertEquals(0, SizeUnit.KILOBYTES.convert(1, SizeUnit.BYTES));
        assertEquals(0, SizeUnit.BYTES.toKilobytes(1));
    }

    @Test
    public void oneByteInMegabytes() throws Exception {
        assertEquals(0, SizeUnit.MEGABYTES.convert(1, SizeUnit.BYTES));
        assertEquals(0, SizeUnit.BYTES.toMegabytes(1));
    }

    @Test
    public void oneByteInGigabytes() throws Exception {
        assertEquals(0, SizeUnit.GIGABYTES.convert(1, SizeUnit.BYTES));
        assertEquals(0, SizeUnit.BYTES.toGigabytes(1));
    }

    @Test
    public void oneByteInTerabytes() throws Exception {
        assertEquals(0, SizeUnit.TERABYTES.convert(1, SizeUnit.BYTES));
        assertEquals(0, SizeUnit.BYTES.toTerabytes(1));
    }

    @Test
    public void oneByteInPetabytes() throws Exception {
        assertEquals(0, SizeUnit.PETABYTES.convert(1, SizeUnit.BYTES));
        assertEquals(0, SizeUnit.BYTES.toPetabytes(1));
    }

    // KILOBYTES

    @Test
    public void oneKilobyteInBytes() throws Exception {
        assertEquals(1024, SizeUnit.BYTES.convert(1, SizeUnit.KILOBYTES));
        assertEquals(1024, SizeUnit.KILOBYTES.toBytes(1));
    }

    @Test
    public void oneKilobyteInKilobytes() throws Exception {
        assertEquals(1, SizeUnit.KILOBYTES.convert(1, SizeUnit.KILOBYTES));
        assertEquals(1L, SizeUnit.KILOBYTES.toKilobytes(1));
    }

    @Test
    public void oneKilobyteInMegabytes() throws Exception {
        assertEquals(0, SizeUnit.MEGABYTES.convert(1, SizeUnit.KILOBYTES));
        assertEquals(0, SizeUnit.KILOBYTES.toMegabytes(1));
    }

    @Test
    public void oneKilobyteInGigabytes() throws Exception {
        assertEquals(0, SizeUnit.GIGABYTES.convert(1, SizeUnit.KILOBYTES));
        assertEquals(0, SizeUnit.KILOBYTES.toGigabytes(1));
    }

    @Test
    public void oneKilobyteInTerabytes() throws Exception {
        assertEquals(0, SizeUnit.TERABYTES.convert(1, SizeUnit.KILOBYTES));
        assertEquals(0, SizeUnit.KILOBYTES.toTerabytes(1));
    }

    @Test
    public void oneKilobyteInPetabytes() throws Exception {
        assertEquals(0, SizeUnit.PETABYTES.convert(1, SizeUnit.KILOBYTES));
        assertEquals(0, SizeUnit.KILOBYTES.toPetabytes(1));
    }

    // MEGABYTES

    @Test
    public void oneMegabyteInBytes() throws Exception {
        assertEquals(1048576, SizeUnit.BYTES.convert(1, SizeUnit.MEGABYTES));
        assertEquals(1048576L, SizeUnit.MEGABYTES.toBytes(1));
    }

    @Test
    public void oneMegabyteInKilobytes() throws Exception {
        assertEquals(1024, SizeUnit.KILOBYTES.convert(1, SizeUnit.MEGABYTES));
        assertEquals(1024, SizeUnit.MEGABYTES.toKilobytes(1));
    }

    @Test
    public void oneMegabyteInMegabytes() throws Exception {
        assertEquals(1, SizeUnit.MEGABYTES.convert(1, SizeUnit.MEGABYTES));
        assertEquals(1, SizeUnit.MEGABYTES.toMegabytes(1));
    }

    @Test
    public void oneMegabyteInGigabytes() throws Exception {
        assertEquals(0, SizeUnit.GIGABYTES.convert(1, SizeUnit.MEGABYTES));
        assertEquals(0, SizeUnit.MEGABYTES.toGigabytes(1));
    }

    @Test
    public void oneMegabyteInTerabytes() throws Exception {
        assertEquals(0, SizeUnit.TERABYTES.convert(1, SizeUnit.MEGABYTES));
        assertEquals(0, SizeUnit.MEGABYTES.toTerabytes(1));
    }

    @Test
    public void oneMegabyteInPetabytes() throws Exception {
        assertEquals(0, SizeUnit.PETABYTES.convert(1, SizeUnit.MEGABYTES));
        assertEquals(0, SizeUnit.MEGABYTES.toPetabytes(1));
    }

    // GIGABYTES

    @Test
    public void oneGigabyteInBytes() throws Exception {
        assertEquals(1073741824, SizeUnit.BYTES.convert(1, SizeUnit.GIGABYTES));
        assertEquals(1073741824, SizeUnit.GIGABYTES.toBytes(1));
    }

    @Test
    public void oneGigabyteInKilobytes() throws Exception {
        assertEquals(1048576, SizeUnit.KILOBYTES.convert(1, SizeUnit.GIGABYTES));
        assertEquals(1048576, SizeUnit.GIGABYTES.toKilobytes(1));
    }

    @Test
    public void oneGigabyteInMegabytes() throws Exception {
        assertEquals(1024, SizeUnit.MEGABYTES.convert(1, SizeUnit.GIGABYTES));
        assertEquals(1024, SizeUnit.GIGABYTES.toMegabytes(1));
    }

    @Test
    public void oneGigabyteInGigabytes() throws Exception {
        assertEquals(1L, SizeUnit.GIGABYTES.convert(1, SizeUnit.GIGABYTES));
        assertEquals(1L, SizeUnit.GIGABYTES.toGigabytes(1));
    }

    @Test
    public void oneGigabyteInTerabytes() throws Exception {
        assertEquals(0, SizeUnit.TERABYTES.convert(1, SizeUnit.GIGABYTES));
        assertEquals(0, SizeUnit.GIGABYTES.toTerabytes(1));
    }

    @Test
    public void oneGigabyteInPetabytes() throws Exception {
        assertEquals(0, SizeUnit.PETABYTES.convert(1, SizeUnit.GIGABYTES));
        assertEquals(0, SizeUnit.GIGABYTES.toPetabytes(1));
    }

    // TERABYTES

    @Test
    public void oneTerabyteInBytes() throws Exception {
        assertEquals(1099511627776L, SizeUnit.BYTES.convert(1, SizeUnit.TERABYTES));
        assertEquals(1099511627776L, SizeUnit.TERABYTES.toBytes(1));
    }

    @Test
    public void oneTerabyteInKilobytes() throws Exception {
        assertEquals(1073741824L, SizeUnit.KILOBYTES.convert(1, SizeUnit.TERABYTES));
        assertEquals(1073741824L, SizeUnit.TERABYTES.toKilobytes(1));
    }

    @Test
    public void oneTerabyteInMegabytes() throws Exception {
        assertEquals(1048576, SizeUnit.MEGABYTES.convert(1, SizeUnit.TERABYTES));
        assertEquals(1048576L, SizeUnit.TERABYTES.toMegabytes(1));
    }

    @Test
    public void oneTerabyteInGigabytes() throws Exception {
        assertEquals(1024, SizeUnit.GIGABYTES.convert(1, SizeUnit.TERABYTES));
        assertEquals(1024, SizeUnit.TERABYTES.toGigabytes(1));
    }

    @Test
    public void oneTerabyteInTerabytes() throws Exception {
        assertEquals(1, SizeUnit.TERABYTES.convert(1, SizeUnit.TERABYTES));
        assertEquals(1, SizeUnit.TERABYTES.toTerabytes(1));
    }

    @Test
    public void oneTerabyteInPetabytes() throws Exception {
        assertEquals(0, SizeUnit.PETABYTES.convert(1, SizeUnit.TERABYTES));
        assertEquals(0, SizeUnit.TERABYTES.toPetabytes(1));
    }

    // PETABYTES

    @Test
    public void onePetabyteInBytes() throws Exception {
        assertEquals(1125899906842624L, SizeUnit.BYTES.convert(1, SizeUnit.PETABYTES));
        assertEquals(1125899906842624L, SizeUnit.PETABYTES.toBytes(1));
    }

    @Test
    public void onePetabyteInKilobytes() throws Exception {
        assertEquals(1099511627776L, SizeUnit.KILOBYTES.convert(1, SizeUnit.PETABYTES));
        assertEquals(1099511627776L, SizeUnit.PETABYTES.toKilobytes(1));
    }

    @Test
    public void onePetabyteInMegabytes() throws Exception {
        assertEquals(1073741824L, SizeUnit.MEGABYTES.convert(1, SizeUnit.PETABYTES));
        assertEquals(1073741824L, SizeUnit.PETABYTES.toMegabytes(1));
    }

    @Test
    public void onePetabyteInGigabytes() throws Exception {
        assertEquals(1048576, SizeUnit.GIGABYTES.convert(1, SizeUnit.PETABYTES));
        assertEquals(1048576L, SizeUnit.PETABYTES.toGigabytes(1));
    }

    @Test
    public void onePetabyteInTerabytes() throws Exception {
        assertEquals(1024, SizeUnit.TERABYTES.convert(1, SizeUnit.PETABYTES));
        assertEquals(1024, SizeUnit.PETABYTES.toTerabytes(1));
    }

    @Test
    public void onePetabyteInPetabytes() throws Exception {
        assertEquals(1, SizeUnit.PETABYTES.convert(1, SizeUnit.PETABYTES));
        assertEquals(1, SizeUnit.PETABYTES.toPetabytes(1));
    }
}
