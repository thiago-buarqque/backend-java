package com.evry.analytics.common.util;

import com.evry.analytics.common.TimeRange;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import java.time.LocalDateTime;

public class TimeRangeUtil {

    public Condition createWhereCondition(Field<LocalDateTime> field, TimeRange timeRange) {
        if (timeRange.equals(TimeRange.LAST_24_HOURS)) {
            return getLast24HoursRange(field);
        }

        LocalDateTime yesterdayLocalDateTimeAtMax = getYesterdayLocalDateTimeAtMax();
        LocalDateTime startDate = yesterdayLocalDateTimeAtMax.minusDays(timeRange.getValue());
        startDate = getAtDayMin(startDate);

        return field.between(
                DSL.localDateTime(startDate), DSL.localDateTime(yesterdayLocalDateTimeAtMax));
    }

    public LocalDateTime getAtDayMax(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withHour(23);
        localDateTime = localDateTime.withMinute(59);
        localDateTime = localDateTime.withSecond(59);

        return localDateTime.withNano(999);
    }

    public LocalDateTime getAtDayMin(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withHour(0);
        localDateTime = localDateTime.withMinute(0);
        localDateTime = localDateTime.withSecond(0);
        return localDateTime.withNano(0);
    }

    private LocalDateTime getYesterdayLocalDateTimeAtMax() {
        LocalDateTime endDate = LocalDateTime.now().minusDays(1);
        return getAtDayMax(endDate);
    }

    private Condition getLast24HoursRange(Field<LocalDateTime> field) {
        LocalDateTime endDate = LocalDateTime.now();

        return field.between(DSL.localDateTime(endDate.minusHours(24)), DSL.localDateTime(endDate));
    }
}
