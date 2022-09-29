package com.evry.analytics.common;

import java.util.Date;

public class TimeRange {
    public TimeRange(Date dateEnd, Date dateStart) {
        if (dateStart == null && dateEnd == null) {
            dateEnd = new Date();
            dateStart = DateUtil.addHours(dateEnd, -24);
        }
        else if (dateStart == null) {
            dateStart = DateUtil.addHours(dateEnd, -24);
        }
        else if (dateEnd == null) {
            dateEnd = DateUtil.addHours(dateStart, 24);
        }

        _dateEnd = dateEnd;
        _dateStart = dateStart;
    }

    public Date getDateEnd() {
        return _dateEnd;
    }

    public Date getDateStart() {
        return _dateStart;
    }

    private final Date _dateEnd;
    private final Date _dateStart;
}
