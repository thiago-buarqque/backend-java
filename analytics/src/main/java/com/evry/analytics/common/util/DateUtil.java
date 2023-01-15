package com.evry.analytics.common.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class DateUtil {
    public static LocalDateTime getAtDayMax(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withHour(23);
        localDateTime = localDateTime.withMinute(59);
        localDateTime = localDateTime.withSecond(59);

        return localDateTime.withNano(999999999);
    }

    public static OffsetDateTime getAtDayMax(OffsetDateTime offsetDateTime) {
        offsetDateTime = offsetDateTime.withHour(23);
        offsetDateTime = offsetDateTime.withMinute(59);
        offsetDateTime = offsetDateTime.withSecond(59);

        return offsetDateTime.withNano(999999999);
    }

    public static ZonedDateTime getAtDayMax(ZonedDateTime zonedDateTime) {
        zonedDateTime = zonedDateTime.withHour(23);
        zonedDateTime = zonedDateTime.withMinute(59);
        zonedDateTime = zonedDateTime.withSecond(59);

        return zonedDateTime.withNano(999999999);
    }

    public static LocalDateTime getAtDayMin(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withHour(0);
        localDateTime = localDateTime.withMinute(0);
        localDateTime = localDateTime.withSecond(0);

        return localDateTime.withNano(0);
    }

    public static OffsetDateTime getAtDayMin(OffsetDateTime offsetDateTime) {
        offsetDateTime = offsetDateTime.withHour(0);
        offsetDateTime = offsetDateTime.withMinute(0);
        offsetDateTime = offsetDateTime.withSecond(0);

        return offsetDateTime.withNano(0);
    }

    public static ZonedDateTime getAtDayMin(ZonedDateTime zonedDateTime) {
        zonedDateTime = zonedDateTime.withHour(0);
        zonedDateTime = zonedDateTime.withMinute(0);
        zonedDateTime = zonedDateTime.withSecond(0);

        return zonedDateTime.withNano(0);
    }
}
