package com.evry.analytics.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.HOUR, hours);

        return calendar.getTime();
    }
}
