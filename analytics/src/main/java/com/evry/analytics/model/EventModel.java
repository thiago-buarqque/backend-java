package com.evry.analytics.model;

import com.evry.analytics.DTO.EventDTO;
import com.evry.analytics.entity.Event;
import com.evry.analytics.repository.EventRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventModel {
    public Event addEvent(EventDTO eventDTO) {
        return _eventRepository.save(
                _objectMapper.convertValue(eventDTO, Event.class));
    }

    public List<Event> getUserEvents(Date date1, Date date2, Long userid) {
        return _eventRepository.findEventsByEventDateBetweenAndUserId(date1,
                date2, userid);
    }

    public List<Event> getAllUserEvents(Long userid) {
        return _eventRepository.findEventsByUserId(userid);
    }

    public Long getTotalEvents() {
        return _eventRepository.count();
    }

    @Autowired
    private EventRepository _eventRepository;

    @Autowired
    private ObjectMapper _objectMapper;
}
