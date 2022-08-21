package com.evry.analytics.restController;

import com.evry.analytics.DTO.EventDTO;
import com.evry.analytics.entity.Event;
import com.evry.analytics.model.EventModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/event")
@RestController
public class EventRestController {

    @GetMapping("/count")
    public long count() {
        return _eventModel.getTotalEvents();
    }

    @GetMapping("/{userId}/all")
    public List<EventDTO> fetchAllUserEvents(
            @PathVariable String userId, @RequestParam(value = "dateEnd",
            required = false) Date dateEnd, @RequestParam(value = "dateStart",
            required = false) Date dateStart) {

        List<Event> events;

        if (dateStart != null && dateEnd != null) {
            events = _eventModel.getUserEvents(dateStart, dateEnd,
                    Long.parseLong(userId));
        }
        else {
            events = _eventModel.getAllUserEvents(Long.parseLong(userId));
        }

        return events.stream().map(EventDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public EventDTO registerEvent(@RequestBody EventDTO eventDTO) {
        if (eventDTO.getEventDate() == null) {
            eventDTO.setEventDate(new Date());
        }

        return _objectMapper.convertValue(_eventModel.addEvent(eventDTO),
                EventDTO.class);
    }

    @Autowired
    private EventModel _eventModel;

    @Autowired
    private ObjectMapper _objectMapper;
}