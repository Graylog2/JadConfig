package com.github.joschi.jadconfig.jodatime;

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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JodaTimeConverterFactoryTest {
    @Test
    public void testConverterMap() {
        final JodaTimeConverterFactory factory = new JodaTimeConverterFactory();
        assertEquals(DateTimeFormatterConverter.class, factory.getConverter(DateTimeFormatter.class));
        assertEquals(DateTimeZoneConverter.class, factory.getConverter(DateTimeZone.class));
        assertEquals(DurationConverter.class, factory.getConverter(Duration.class));
        assertEquals(IntervalConverter.class, factory.getConverter(Interval.class));
        assertEquals(PeriodConverter.class, factory.getConverter(Period.class));
        assertEquals(SecondsConverter.class, factory.getConverter(Seconds.class));
        assertEquals(MinutesConverter.class, factory.getConverter(Minutes.class));
        assertEquals(HoursConverter.class, factory.getConverter(Hours.class));
        assertEquals(DaysConverter.class, factory.getConverter(Days.class));
        assertEquals(WeeksConverter.class, factory.getConverter(Weeks.class));
        assertEquals(MonthsConverter.class, factory.getConverter(Months.class));
        assertEquals(YearsConverter.class, factory.getConverter(Years.class));
    }
}
