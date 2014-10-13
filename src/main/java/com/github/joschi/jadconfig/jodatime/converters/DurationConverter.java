package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allow durations to be passed in as number + unit or as ISO8601 duration (standard seconds with millis).
 * <p>Examples:
 * <ul>
 * <li>{@code 23s} &rarr; {@code Duration.standardSeconds(23)}</li>
 * <li>{@code 30m} &rarr; {@code Duration.standardMinutes(30)}</li>
 * <li>{@code 30h} &rarr; {@code Duration.standardHours(30)}</li>
 * <li>{@code 30d} &rarr; {@code Duration.standardDays(30)}</li>
 * </ul>
 *
 * @see org.joda.time.Duration#parse(String)
 */
public class DurationConverter implements Converter<Duration> {
    private static final Pattern DURATION_PATTERN = Pattern.compile("(\\d+)\\s*(\\w+)");
    private static final Map<String, TimeUnit> SUFFIXES = new HashMap<String, TimeUnit>();

    static {
        SUFFIXES.put("s", TimeUnit.SECONDS);
        SUFFIXES.put("S", TimeUnit.SECONDS);
        SUFFIXES.put("sec", TimeUnit.SECONDS);
        SUFFIXES.put("secs", TimeUnit.SECONDS);
        SUFFIXES.put("second", TimeUnit.SECONDS);
        SUFFIXES.put("seconds", TimeUnit.SECONDS);
        SUFFIXES.put("m", TimeUnit.MINUTES);
        SUFFIXES.put("M", TimeUnit.MINUTES);
        SUFFIXES.put("min", TimeUnit.MINUTES);
        SUFFIXES.put("minute", TimeUnit.MINUTES);
        SUFFIXES.put("minutes", TimeUnit.MINUTES);
        SUFFIXES.put("h", TimeUnit.HOURS);
        SUFFIXES.put("H", TimeUnit.HOURS);
        SUFFIXES.put("hr", TimeUnit.HOURS);
        SUFFIXES.put("hour", TimeUnit.HOURS);
        SUFFIXES.put("hours", TimeUnit.HOURS);
        SUFFIXES.put("d", TimeUnit.DAYS);
        SUFFIXES.put("D", TimeUnit.DAYS);
        SUFFIXES.put("day", TimeUnit.DAYS);
        SUFFIXES.put("days", TimeUnit.DAYS);
    }

    @Override
    public Duration convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to Duration object.");
        }

        if (value.startsWith("PT")) {
            // pure ISO format, try to parse it directly
            return Duration.parse(value);
        } else {
            final Matcher matcher = DURATION_PATTERN.matcher(value);
            if (matcher.matches() && matcher.groupCount() >= 2) {
                final long longValue = Long.valueOf(matcher.group(1));
                final String suffix = matcher.group(2);

                final TimeUnit timeUnit = SUFFIXES.get(suffix);
                if (timeUnit == null) {
                    throw new ParameterException("Couldn't convert value \"" + value + "\" to Duration object, invalid unit.");
                }

                switch (timeUnit) {
                    case DAYS:
                        return Duration.standardDays(longValue);
                    case HOURS:
                        return Duration.standardHours(longValue);
                    case MINUTES:
                        return Duration.standardMinutes(longValue);
                    case SECONDS:
                        return Duration.standardSeconds(longValue);
                }
            }
        }

        throw new ParameterException("Couldn't convert value \"" + value + "\" to Duration object.");
    }

    @Override
    public String convertTo(Duration value) {
        return value.toString();
    }
}
