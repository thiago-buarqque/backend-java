package com.evry.analytics.common;

import java.util.HashMap;
import java.util.Map;

public class TimeRange {

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public TimeRange(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public TimeRange of(String key) {
        return TIME_RANGE_MAP.getOrDefault(key, null);
    }

    private final String key;
    private final int value;

    private static final Map<String, TimeRange> TIME_RANGE_MAP =
            new HashMap<String, TimeRange>() {
                {
                    put("last-24-hours", LAST_24_HOURS);
                    put("last-day", LAST_DAY);
                    put("last-7-days", LAST_7_DAYS);
                    put("last-14-days", LAST_14_DAYS);
                    put("last-30-days", LAST_30_DAYS);
                    put("last-90-days", LAST_90_DAYS);
                    put("last-180-days", LAST_180_DAYS);
                    put("last-365-days", LAST_365_DAYS);
                }
            };

    public static final TimeRange LAST_24_HOURS = new TimeRange("last-24-hours", 0);
    public static final TimeRange LAST_DAY = new TimeRange("last-day", 0);
    public static final TimeRange LAST_7_DAYS = new TimeRange("last-7-days", 7);
    public static final TimeRange LAST_14_DAYS = new TimeRange("last-14-days", 14);
    public static final TimeRange LAST_30_DAYS = new TimeRange("last-30-days", 30);
    public static final TimeRange LAST_90_DAYS = new TimeRange("last-90-days", 90);
    public static final TimeRange LAST_180_DAYS = new TimeRange("last-180-days", 180);
    public static final TimeRange LAST_365_DAYS = new TimeRange("last-365-days", 365);
}
