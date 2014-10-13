package com.github.joschi.jadconfig.util;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class DurationTest {
    @Test
    public void convertsDays() throws Exception {
        assertEquals(2l, Duration.days(2).toDays());
        assertEquals(48, Duration.days(2).toHours());
    }

    @Test
    public void convertsHours() throws Exception {
        assertEquals(120, Duration.hours(2).toMinutes());
    }

    @Test
    public void convertsMinutes() throws Exception {
        assertEquals(180, Duration.minutes(3).toSeconds());
    }

    @Test
    public void convertsSeconds() throws Exception {
        assertEquals(2000, Duration.seconds(2).toMilliseconds());
    }

    @Test
    public void convertsMilliseconds() throws Exception {
        assertEquals(2000, Duration.milliseconds(2).toMicroseconds());
    }

    @Test
    public void convertsMicroseconds() throws Exception {
        assertEquals(2000, Duration.microseconds(2).toNanoseconds());
    }

    @Test
    public void convertsNanoseconds() throws Exception {
        assertEquals(2, Duration.nanoseconds(2).toNanoseconds());
    }

    @Test
    public void parsesDays() throws Exception {
        assertEquals(Duration.days(1), Duration.parse("1d"));
        assertEquals(Duration.days(1), Duration.parse("1 day"));
        assertEquals(Duration.days(2), Duration.parse("2 days"));
    }

    @Test
    public void parsesHours() throws Exception {
        assertEquals(Duration.hours(1), Duration.parse("1h"));
        assertEquals(Duration.hours(1), Duration.parse("1 hour"));
        assertEquals(Duration.hours(2), Duration.parse("2 hours"));
    }

    @Test
    public void parsesMinutes() throws Exception {
        assertEquals(Duration.minutes(1), Duration.parse("1m"));
        assertEquals(Duration.minutes(1), Duration.parse("1 minute"));
        assertEquals(Duration.minutes(2), Duration.parse("2 minutes"));
    }

    @Test
    public void parsesSeconds() throws Exception {
        assertEquals(Duration.seconds(1), Duration.parse("1s"));
        assertEquals(Duration.seconds(1), Duration.parse("1 second"));
        assertEquals(Duration.seconds(2), Duration.parse("2 seconds"));
    }

    @Test
    public void parsesMilliseconds() throws Exception {
        assertEquals(Duration.milliseconds(1), Duration.parse("1ms"));
        assertEquals(Duration.milliseconds(1), Duration.parse("1 millisecond"));
        assertEquals(Duration.milliseconds(2), Duration.parse("2 milliseconds"));
    }

    @Test
    public void parsesMicroseconds() throws Exception {
        assertEquals(Duration.microseconds(1), Duration.parse("1us"));
        assertEquals(Duration.microseconds(1), Duration.parse("1 microsecond"));
        assertEquals(Duration.microseconds(2), Duration.parse("2 microseconds"));
    }

    @Test
    public void parsesNanoseconds() throws Exception {
        assertEquals(Duration.nanoseconds(1), Duration.parse("1ns"));
        assertEquals(Duration.nanoseconds(1), Duration.parse("1 nanosecond"));
        assertEquals(Duration.nanoseconds(2), Duration.parse("2 nanoseconds"));
    }

    @Test
    public void parseDurationWithWhiteSpaces() {
        assertEquals(Duration.seconds(5), Duration.parse("5   seconds"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongDurationCount() {
        Duration.parse("five seconds");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongDurationTimeUnit() {
        Duration.parse("1gs");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongDurationFormat() {
        Duration.parse("1 milli second");
    }

    @Test
    public void isHumanReadable() throws Exception {
        assertEquals("1 microsecond", Duration.microseconds(1).toString());
        assertEquals("3 microseconds", Duration.microseconds(3).toString());
    }

    @Test
    public void hasAQuantity() throws Exception {
        assertEquals(12, Duration.microseconds(12).getQuantity());
    }

    @Test
    public void hasAUnit() throws Exception {
        assertEquals(TimeUnit.MICROSECONDS, Duration.microseconds(1).getUnit());
    }
}
