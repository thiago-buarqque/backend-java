package com.evry.analytics.model;

import com.evry.analytics.entity.Event;
import com.evry.analytics.repository.EventRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventModel {

    public EventModel(EventRepository eventRepository) {
        _eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return _eventRepository.save(event);
    }

    public List<Event> getUserEvents(
            LocalDateTime dateEnd, LocalDateTime dateStart, String userid) {

        if (dateEnd == null) {
            dateEnd = LocalDateTime.now();
        }

        if (dateStart == null) {
            dateStart = dateEnd.minusHours(24);
        }

        return _eventRepository.findEventsByDateTimeBetweenAndVisitorId(dateStart, dateEnd, userid);
    }

    private final EventRepository _eventRepository;
}
