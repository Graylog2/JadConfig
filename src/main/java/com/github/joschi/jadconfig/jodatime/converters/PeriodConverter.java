package com.github.joschi.jadconfig.jodatime.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.joda.time.Period;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allow periods to be passed in as number + unit or as a {@link org.joda.time.format.ISOPeriodFormat} (standard, without millis).
 * <p>Examples:
 * <ul>
 * <li>{@code 23s} &rarr; {@code Period.seconds(23)}</li>
 * <li>{@code 30m} &rarr; {@code Period.minutes(30)}</li>
 * <li>{@code 30h} &rarr; {@code Period.hours(30)}</li>
 * <li>{@code 30d} &rarr; {@code Period.days(30)}</li>
 * <li>{@code 2w} &rarr; {@code Period.weeks(2)}</li>
 * <li>{@code 1y} &rarr; {@code Period.years(1)}</li>
 * </ul>
 *
 * @see org.joda.time.format.ISOPeriodFormat
 */
public class PeriodConverter implements Converter<Period> {
    private static final Pattern PERIOD_PATTERN = Pattern.compile("(\\d+)\\s*(\\w+)");
    private static final Map<String, Boolean> SUFFIXES = new HashMap<String, Boolean>();
    private static final Map<String, Character> NORMALIZED_SUFFIXES = new HashMap<String, Character>();

    static {
        SUFFIXES.put("s", true);
        SUFFIXES.put("S", true);
        SUFFIXES.put("sec", true);
        SUFFIXES.put("secs", true);
        SUFFIXES.put("second", true);
        SUFFIXES.put("seconds", true);
        SUFFIXES.put("m", true);
        SUFFIXES.put("M", true);
        SUFFIXES.put("min", true);
        SUFFIXES.put("minute", true);
        SUFFIXES.put("minutes", true);
        SUFFIXES.put("h", true);
        SUFFIXES.put("H", true);
        SUFFIXES.put("hr", true);
        SUFFIXES.put("hour", true);
        SUFFIXES.put("hours", true);
        SUFFIXES.put("d", false);
        SUFFIXES.put("D", false);
        SUFFIXES.put("day", false);
        SUFFIXES.put("days", false);
        SUFFIXES.put("w", false);
        SUFFIXES.put("W", false);
        SUFFIXES.put("week", false);
        SUFFIXES.put("weeks", false);
        SUFFIXES.put("month", false);
        SUFFIXES.put("months", false);
        SUFFIXES.put("y", false);
        SUFFIXES.put("Y", false);
        SUFFIXES.put("year", false);
        SUFFIXES.put("years", false);

        NORMALIZED_SUFFIXES.put("s", 'S');
        NORMALIZED_SUFFIXES.put("S", 'S');
        NORMALIZED_SUFFIXES.put("sec", 'S');
        NORMALIZED_SUFFIXES.put("secs", 'S');
        NORMALIZED_SUFFIXES.put("second", 'S');
        NORMALIZED_SUFFIXES.put("seconds", 'S');
        NORMALIZED_SUFFIXES.put("m", 'M');
        NORMALIZED_SUFFIXES.put("M", 'M');
        NORMALIZED_SUFFIXES.put("min", 'M');
        NORMALIZED_SUFFIXES.put("minute", 'M');
        NORMALIZED_SUFFIXES.put("minutes", 'M');
        NORMALIZED_SUFFIXES.put("h", 'H');
        NORMALIZED_SUFFIXES.put("H", 'H');
        NORMALIZED_SUFFIXES.put("hr", 'H');
        NORMALIZED_SUFFIXES.put("hour", 'H');
        NORMALIZED_SUFFIXES.put("hours", 'H');
        NORMALIZED_SUFFIXES.put("d", 'D');
        NORMALIZED_SUFFIXES.put("D", 'D');
        NORMALIZED_SUFFIXES.put("day", 'D');
        NORMALIZED_SUFFIXES.put("days", 'D');
        NORMALIZED_SUFFIXES.put("w", 'W');
        NORMALIZED_SUFFIXES.put("W", 'W');
        NORMALIZED_SUFFIXES.put("week", 'W');
        NORMALIZED_SUFFIXES.put("weeks", 'W');
        NORMALIZED_SUFFIXES.put("month", 'M');
        NORMALIZED_SUFFIXES.put("months", 'M');
        NORMALIZED_SUFFIXES.put("y", 'Y');
        NORMALIZED_SUFFIXES.put("Y", 'Y');
        NORMALIZED_SUFFIXES.put("year", 'Y');
        NORMALIZED_SUFFIXES.put("years", 'Y');
    }

    @Override
    public Period convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to Period object.");
        }

        final Period period;
        if (value.startsWith("P")) {
            // pure IsoPeriod format, try to parse it directly
            period = Period.parse(value);
        } else {
            final Matcher matcher = PERIOD_PATTERN.matcher(value);
            if (matcher.matches() && matcher.groupCount() >= 2) {
                final long duration = Long.valueOf(matcher.group(1));
                final String suffix = matcher.group(2);

                final StringBuilder asIsoFormat = new StringBuilder("P");

                final Boolean prefixNecessary = SUFFIXES.get(suffix);
                if (prefixNecessary == null) {
                    throw new ParameterException("Couldn't convert value \"" + value + "\" to Period object, invalid unit.");
                } else if (prefixNecessary) {
                    asIsoFormat.append('T');
                }

                final Character normalizedSuffix = NORMALIZED_SUFFIXES.get(suffix);
                if (normalizedSuffix == null) {
                    throw new ParameterException("Couldn't convert value \"" + value + "\" to Period object, invalid unit.");
                }

                asIsoFormat.append(duration).append(normalizedSuffix);
                period = Period.parse(asIsoFormat.toString());
            } else {
                period = null;
            }
        }

        if (period == null) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to Period object.");
        }

        return period;
    }

    @Override
    public String convertTo(Period value) {
        return value.toString();
    }
}
