package com.github.joschi.jadconfig.util;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    
    @Test
        public void isComparable() throws Exception {
            // both zero
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.nanoseconds(0).compareTo(Duration.days(0)));;
    
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.microseconds(0).compareTo(Duration.days(0)));;
    
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.milliseconds(0).compareTo(Duration.days(0)));;
    
            assertEquals(0, Duration.seconds(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.seconds(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.seconds(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.seconds(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.seconds(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.seconds(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.seconds(0).compareTo(Duration.days(0)));;
    
            assertEquals(0, Duration.minutes(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.minutes(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.minutes(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.minutes(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.minutes(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.minutes(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.minutes(0).compareTo(Duration.days(0)));;
    
            assertEquals(0, Duration.hours(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.hours(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.hours(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.hours(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.hours(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.hours(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.hours(0).compareTo(Duration.days(0)));;
    
            assertEquals(0, Duration.days(0).compareTo(Duration.nanoseconds(0)));;
            assertEquals(0, Duration.days(0).compareTo(Duration.microseconds(0)));;
            assertEquals(0, Duration.days(0).compareTo(Duration.milliseconds(0)));;
            assertEquals(0, Duration.days(0).compareTo(Duration.seconds(0)));;
            assertEquals(0, Duration.days(0).compareTo(Duration.minutes(0)));;
            assertEquals(0, Duration.days(0).compareTo(Duration.hours(0)));;
            assertEquals(0, Duration.days(0).compareTo(Duration.days(0)));;
    
            // one zero, one negative
        
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.microseconds(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.milliseconds(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.seconds(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.minutes(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.hours(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.hours(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.hours(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.hours(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.hours(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.hours(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.hours(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.days(0).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.days(0).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.days(0).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.days(0).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.days(0).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.days(0).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.days(0).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.days(0)) < 0);
    
            assertTrue(Duration.microseconds(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.days(0)) < 0);
    
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.days(0)) < 0);
    
            assertTrue(Duration.seconds(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.days(0)) < 0);
    
            assertTrue(Duration.minutes(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.days(0)) < 0);
    
            assertTrue(Duration.hours(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.days(0)) < 0);
    
            assertTrue(Duration.days(-1).compareTo(Duration.nanoseconds(0)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.microseconds(0)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.milliseconds(0)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.seconds(0)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.minutes(0)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.hours(0)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.days(0)) < 0);
    
            // one zero, one positive
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.nanoseconds(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.microseconds(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.microseconds(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.milliseconds(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.milliseconds(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.seconds(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.seconds(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.minutes(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.minutes(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.hours(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.hours(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.hours(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.hours(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.hours(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.hours(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.hours(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.days(0).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.days(0).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.days(0).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.days(0).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.days(0).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.days(0).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.days(0).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.days(0)) > 0);
    
            assertTrue(Duration.microseconds(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.days(0)) > 0);
    
            assertTrue(Duration.milliseconds(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.days(0)) > 0);
    
            assertTrue(Duration.seconds(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.days(0)) > 0);
    
            assertTrue(Duration.minutes(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.days(0)) > 0);
    
            assertTrue(Duration.hours(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.days(0)) > 0);
    
            assertTrue(Duration.days(1).compareTo(Duration.nanoseconds(0)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.microseconds(0)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.milliseconds(0)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.seconds(0)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.minutes(0)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.hours(0)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.days(0)) > 0);
    
            // both negative
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.nanoseconds(-2).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.microseconds(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.microseconds(-2).compareTo(Duration.microseconds(-1)) < 0);
            assertTrue(Duration.microseconds(-2).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.microseconds(-2).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.microseconds(-2).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.microseconds(-2).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.microseconds(-2).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.microseconds(-1)) < 0);
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.milliseconds(-1)) < 0);
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.milliseconds(-2).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.seconds(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.seconds(-2).compareTo(Duration.microseconds(-1)) < 0);
            assertTrue(Duration.seconds(-2).compareTo(Duration.milliseconds(-1)) < 0);
            assertTrue(Duration.seconds(-2).compareTo(Duration.seconds(-1)) < 0);
            assertTrue(Duration.seconds(-2).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.seconds(-2).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.seconds(-2).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.minutes(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.minutes(-2).compareTo(Duration.microseconds(-1)) < 0);
            assertTrue(Duration.minutes(-2).compareTo(Duration.milliseconds(-1)) < 0);
            assertTrue(Duration.minutes(-2).compareTo(Duration.seconds(-1)) < 0);
            assertTrue(Duration.minutes(-2).compareTo(Duration.minutes(-1)) < 0);
            assertTrue(Duration.minutes(-2).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.minutes(-2).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.hours(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.hours(-2).compareTo(Duration.microseconds(-1)) < 0);
            assertTrue(Duration.hours(-2).compareTo(Duration.milliseconds(-1)) < 0);
            assertTrue(Duration.hours(-2).compareTo(Duration.seconds(-1)) < 0);
            assertTrue(Duration.hours(-2).compareTo(Duration.minutes(-1)) < 0);
            assertTrue(Duration.hours(-2).compareTo(Duration.hours(-1)) < 0);
            assertTrue(Duration.hours(-2).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.days(-2).compareTo(Duration.nanoseconds(-1)) < 0);
            assertTrue(Duration.days(-2).compareTo(Duration.microseconds(-1)) < 0);
            assertTrue(Duration.days(-2).compareTo(Duration.milliseconds(-1)) < 0);
            assertTrue(Duration.days(-2).compareTo(Duration.seconds(-1)) < 0);
            assertTrue(Duration.days(-2).compareTo(Duration.minutes(-1)) < 0);
            assertTrue(Duration.days(-2).compareTo(Duration.hours(-1)) < 0);
            assertTrue(Duration.days(-2).compareTo(Duration.days(-1)) < 0);
    
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.nanoseconds(-2)) > 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.microseconds(-2)) > 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.milliseconds(-2)) > 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.seconds(-2)) > 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.minutes(-2)) > 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.hours(-2)) > 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.days(-2)) > 0);
    
            assertTrue(Duration.microseconds(-1).compareTo(Duration.nanoseconds(-2)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.microseconds(-2)) > 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.milliseconds(-2)) > 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.seconds(-2)) > 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.minutes(-2)) > 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.hours(-2)) > 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.days(-2)) > 0);
    
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.nanoseconds(-2)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.microseconds(-2)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.milliseconds(-2)) > 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.seconds(-2)) > 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.minutes(-2)) > 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.hours(-2)) > 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.days(-2)) > 0);
    
            assertTrue(Duration.seconds(-1).compareTo(Duration.nanoseconds(-2)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.microseconds(-2)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.milliseconds(-2)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.seconds(-2)) > 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.minutes(-2)) > 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.hours(-2)) > 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.days(-2)) > 0);
    
            assertTrue(Duration.minutes(-1).compareTo(Duration.nanoseconds(-2)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.microseconds(-2)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.milliseconds(-2)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.seconds(-2)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.minutes(-2)) > 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.hours(-2)) > 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.days(-2)) > 0);
    
            assertTrue(Duration.hours(-1).compareTo(Duration.nanoseconds(-2)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.microseconds(-2)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.milliseconds(-2)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.seconds(-2)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.minutes(-2)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.hours(-2)) > 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.days(-2)) > 0);
    
            assertTrue(Duration.days(-1).compareTo(Duration.nanoseconds(-2)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.microseconds(-2)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.milliseconds(-2)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.seconds(-2)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.minutes(-2)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.hours(-2)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.days(-2)) > 0);
    
            // both positive
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.nanoseconds((2))) < 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.microseconds((2))) < 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.milliseconds((2))) < 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.seconds((2))) < 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.minutes((2))) < 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.hours((2))) < 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.microseconds(1).compareTo(Duration.nanoseconds((2))) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.microseconds((2))) < 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.milliseconds((2))) < 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.seconds((2))) < 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.minutes((2))) < 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.hours((2))) < 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.milliseconds(1).compareTo(Duration.nanoseconds((2))) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.microseconds((2))) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.milliseconds((2))) < 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.seconds((2))) < 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.minutes((2))) < 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.hours((2))) < 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.seconds(1).compareTo(Duration.nanoseconds((2))) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.microseconds((2))) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.milliseconds((2))) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.seconds((2))) < 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.minutes((2))) < 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.hours((2))) < 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.minutes(1).compareTo(Duration.nanoseconds((2))) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.microseconds((2))) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.milliseconds((2))) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.seconds((2))) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.minutes((2))) < 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.hours((2))) < 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.hours(1).compareTo(Duration.nanoseconds((2))) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.microseconds((2))) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.milliseconds((2))) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.seconds((2))) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.minutes((2))) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.hours((2))) < 0);
            assertTrue(Duration.hours(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.days(1).compareTo(Duration.nanoseconds((2))) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.microseconds((2))) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.milliseconds((2))) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.seconds((2))) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.minutes((2))) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.hours((2))) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.days((2))) < 0);
    
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.microseconds((1))) < 0);
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.milliseconds((1))) < 0);
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.seconds((1))) < 0);
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.minutes((1))) < 0);
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.hours((1))) < 0);
            assertTrue(Duration.nanoseconds(2).compareTo(Duration.days((1))) < 0);
    
            assertTrue(Duration.microseconds(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.microseconds(2).compareTo(Duration.microseconds((1))) > 0);
            assertTrue(Duration.microseconds(2).compareTo(Duration.milliseconds((1))) < 0);
            assertTrue(Duration.microseconds(2).compareTo(Duration.seconds((1))) < 0);
            assertTrue(Duration.microseconds(2).compareTo(Duration.minutes((1))) < 0);
            assertTrue(Duration.microseconds(2).compareTo(Duration.hours((1))) < 0);
            assertTrue(Duration.microseconds(2).compareTo(Duration.days((1))) < 0);
    
            assertTrue(Duration.milliseconds(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.milliseconds(2).compareTo(Duration.microseconds((1))) > 0);
            assertTrue(Duration.milliseconds(2).compareTo(Duration.milliseconds((1))) > 0);
            assertTrue(Duration.milliseconds(2).compareTo(Duration.seconds((1))) < 0);
            assertTrue(Duration.milliseconds(2).compareTo(Duration.minutes((1))) < 0);
            assertTrue(Duration.milliseconds(2).compareTo(Duration.hours((1))) < 0);
            assertTrue(Duration.milliseconds(2).compareTo(Duration.days((1))) < 0);
    
            assertTrue(Duration.seconds(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.seconds(2).compareTo(Duration.microseconds((1))) > 0);
            assertTrue(Duration.seconds(2).compareTo(Duration.milliseconds((1))) > 0);
            assertTrue(Duration.seconds(2).compareTo(Duration.seconds((1))) > 0);
            assertTrue(Duration.seconds(2).compareTo(Duration.minutes((1))) < 0);
            assertTrue(Duration.seconds(2).compareTo(Duration.hours((1))) < 0);
            assertTrue(Duration.seconds(2).compareTo(Duration.days((1))) < 0);
    
            assertTrue(Duration.minutes(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.minutes(2).compareTo(Duration.microseconds((1))) > 0);
            assertTrue(Duration.minutes(2).compareTo(Duration.milliseconds((1))) > 0);
            assertTrue(Duration.minutes(2).compareTo(Duration.seconds((1))) > 0);
            assertTrue(Duration.minutes(2).compareTo(Duration.minutes((1))) > 0);
            assertTrue(Duration.minutes(2).compareTo(Duration.hours((1))) < 0);
            assertTrue(Duration.minutes(2).compareTo(Duration.days((1))) < 0);
    
            assertTrue(Duration.hours(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.hours(2).compareTo(Duration.microseconds((1))) > 0);
            assertTrue(Duration.hours(2).compareTo(Duration.milliseconds((1))) > 0);
            assertTrue(Duration.hours(2).compareTo(Duration.seconds((1))) > 0);
            assertTrue(Duration.hours(2).compareTo(Duration.minutes((1))) > 0);
            assertTrue(Duration.hours(2).compareTo(Duration.hours((1))) > 0);
            assertTrue(Duration.hours(2).compareTo(Duration.days((1))) < 0);
    
            assertTrue(Duration.days(2).compareTo(Duration.nanoseconds((1))) > 0);
            assertTrue(Duration.days(2).compareTo(Duration.microseconds((1))) > 0);
            assertTrue(Duration.days(2).compareTo(Duration.milliseconds((1))) > 0);
            assertTrue(Duration.days(2).compareTo(Duration.seconds((1))) > 0);
            assertTrue(Duration.days(2).compareTo(Duration.minutes((1))) > 0);
            assertTrue(Duration.days(2).compareTo(Duration.hours((1))) > 0);
            assertTrue(Duration.days(2).compareTo(Duration.days((1))) > 0);
    
            // one negative, one positive
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.nanoseconds(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.microseconds(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.microseconds(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.milliseconds(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.seconds(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.seconds(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.minutes(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.minutes(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.hours(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.hours(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.days(-1).compareTo(Duration.nanoseconds(1)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.microseconds(1)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.milliseconds(1)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.seconds(1)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.minutes(1)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.hours(1)) < 0);
            assertTrue(Duration.days(-1).compareTo(Duration.days(1)) < 0);
    
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.nanoseconds(1).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.microseconds(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.microseconds(1).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.milliseconds(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.milliseconds(1).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.seconds(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.seconds(1).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.minutes(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.minutes(1).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.hours(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.hours(1).compareTo(Duration.days(-1)) > 0);
    
            assertTrue(Duration.days(1).compareTo(Duration.nanoseconds(-1)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.microseconds(-1)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.milliseconds(-1)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.seconds(-1)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.minutes(-1)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.hours(-1)) > 0);
            assertTrue(Duration.days(1).compareTo(Duration.days(-1)) > 0);
        }
}
