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
        startDate = DateUtil.getAtDayMin(startDate);

        return field.between(
                DSL.localDateTime(startDate), DSL.localDateTime(yesterdayLocalDateTimeAtMax));
    }

    private LocalDateTime getYesterdayLocalDateTimeAtMax() {
        LocalDateTime endDate = LocalDateTime.now();

        return DateUtil.getAtDayMax(endDate.minusDays(1));
    }

    private Condition getLast24HoursRange(Field<LocalDateTime> field) {
        LocalDateTime endDate = LocalDateTime.now();

        return field.between(DSL.localDateTime(endDate.minusHours(24)), DSL.localDateTime(endDate));
    }
}
