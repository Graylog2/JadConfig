package com.github.joschi.jadconfig.jodatime;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ConverterFactory;
import com.github.joschi.jadconfig.jodatime.converters.DateTimeFormatterConverter;
import com.github.joschi.jadconfig.jodatime.converters.DateTimeZoneConverter;
import com.github.joschi.jadconfig.jodatime.converters.DaysConverter;
import com.github.joschi.jadconfig.jodatime.converters.DurationConverter;
import com.github.joschi.jadconfig.jodatime.converters.HoursConverter;
import com.github.joschi.jadconfig.jodatime.converters.IntervalConverter;
import com.github.joschi.jadconfig.jodatime.converters.MinutesConverter;
import com.github.joschi.jadconfig.jodatime.converters.MonthsConverter;
import com.github.joschi.jadconfig.jodatime.converters.PeriodConverter;
import com.github.joschi.jadconfig.jodatime.converters.SecondsConverter;
import com.github.joschi.jadconfig.jodatime.converters.WeeksConverter;
import com.github.joschi.jadconfig.jodatime.converters.YearsConverter;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ConverterFactory} for holding the {@link Converter} classes for Joda-Time support.
 * <p>
 * Supported {@link com.github.joschi.jadconfig.Converter} types are:
 * <ul>
 * <li>{@link DateTimeFormatter} through {@link DateTimeFormatterConverter}</li>
 * <li>{@link DateTimeZone} through {@link DateTimeZoneConverter}</li>
 * <li>{@link Duration} through {@link DurationConverter}</li>
 * <li>{@link Interval} through {@link IntervalConverter}</li>
 * <li>{@link Period} through {@link PeriodConverter}</li>
 * <li>{@link Seconds} through {@link SecondsConverter}</li>
 * <li>{@link Minutes} through {@link MinutesConverter}</li>
 * <li>{@link Hours} through {@link HoursConverter}</li>
 * <li>{@link Days} through {@link DaysConverter}</li>
 * <li>{@link Weeks} through {@link WeeksConverter}</li>
 * <li>{@link Months} through {@link MonthsConverter}</li>
 * <li>{@link Years} through {@link YearsConverter}</li>
 * </ul>
 * </p>
 *
 * @author jschalanda
 */
public class JodaTimeConverterFactory implements ConverterFactory {
    private static final Map<Class, Class<? extends Converter<?>>> JODA_TIME_CONVERTERS;

    static {
        JODA_TIME_CONVERTERS = new HashMap<Class, Class<? extends Converter<?>>>();
        JODA_TIME_CONVERTERS.put(DateTimeFormatter.class, DateTimeFormatterConverter.class);
        JODA_TIME_CONVERTERS.put(DateTimeZone.class, DateTimeZoneConverter.class);
        JODA_TIME_CONVERTERS.put(Duration.class, DurationConverter.class);
        JODA_TIME_CONVERTERS.put(Interval.class, IntervalConverter.class);
        JODA_TIME_CONVERTERS.put(Period.class, PeriodConverter.class);
        JODA_TIME_CONVERTERS.put(Seconds.class, SecondsConverter.class);
        JODA_TIME_CONVERTERS.put(Minutes.class, MinutesConverter.class);
        JODA_TIME_CONVERTERS.put(Hours.class, HoursConverter.class);
        JODA_TIME_CONVERTERS.put(Days.class, DaysConverter.class);
        JODA_TIME_CONVERTERS.put(Weeks.class, WeeksConverter.class);
        JODA_TIME_CONVERTERS.put(Months.class, MonthsConverter.class);
        JODA_TIME_CONVERTERS.put(Years.class, YearsConverter.class);
    }

    /**
     * Finds and returns a {@link com.github.joschi.jadconfig.Converter} for the provided {@literal classType}.
     *
     * @param classType The class type for which to find and return a {@link com.github.joschi.jadconfig.Converter}
     * @return A {@link com.github.joschi.jadconfig.Converter} for the requested class type
     */
    @SuppressWarnings("unchecked")
    public <T> Class<? extends Converter<T>> getConverter(Class<T> classType) {
        return (Class<? extends Converter<T>>) JODA_TIME_CONVERTERS.get(classType);
    }
}