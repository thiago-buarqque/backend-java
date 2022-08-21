package com.evry.analytics.model;

import com.evry.analytics.entity.Event;
import com.evry.analytics.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventModel {
    public Event addEvent(Event event) {
        return _eventRepository.save(event);
    }

    public List<Event> getUserEvents(Date date1, Date date2, Long userid) {
        return _eventRepository.findEventsByEventDateBetweenAndUserId(date1,
                date2, userid);
    }

    @Autowired
    private EventRepository _eventRepository;
}
