package com.evry.analytics.service;

import com.evry.analytics.model.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    Event addEvent(Event event);

    List<Event> getVisitorEvents(
            LocalDateTime dateEnd, LocalDateTime dateStart, String visitorId);

}
