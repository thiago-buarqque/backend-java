package com.evry.analytics.model;

import com.evry.analytics.common.TimeRange;
import com.evry.analytics.entity.Event;
import com.evry.analytics.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventModel {
    public Event addEvent(Event event) {
        return _eventRepository.save(event);
    }

    public List<Event> getUserEvents(TimeRange timeRange, Long userid) {
        return _eventRepository.findEventsByEventDateBetweenAndUserId(
                timeRange.getDateStart(), timeRange.getDateEnd(), userid);
    }

    @Autowired
    private EventRepository _eventRepository;
}
