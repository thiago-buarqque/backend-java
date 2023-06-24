package com.evry.analytics.service;

import com.evry.analytics.entity.Event;
import com.evry.analytics.repository.EventRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventService {

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getVisitorEvents(
            LocalDateTime dateEnd, LocalDateTime dateStart, String visitorId) {

        if (dateEnd == null) {
            dateEnd = LocalDateTime.now();
        }

        if (dateStart == null) {
            dateStart = dateEnd.minusHours(24);
        }

        return eventRepository.findEventsByDateTimeBetweenAndVisitorId(dateStart, dateEnd, visitorId);
    }

    private final EventRepository eventRepository;
}
