/*
 * Thiago buarque projects
 *
 * Github: https://github.com/thiago-buarqque
 */

package com.evry.analytics.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date addHours(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.HOUR, hours);

        return c.getTime();
    }
}
