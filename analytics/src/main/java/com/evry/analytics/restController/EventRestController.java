package com.evry.analytics.restController;

import com.evry.analytics.DTO.EventDTO;
import com.evry.analytics.entity.Event;
import com.evry.analytics.model.EventModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RequestMapping("/event")
@RestController
public class EventRestController extends BaseRestController {

    public EventRestController(EventModel eventModel, ObjectMapper objectMapper) {
        this.eventModel = eventModel;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{userId}")
    public List<EventDTO> fetchAllUserEvents(
            @PathVariable String userId,
            @RequestParam(required = false) LocalDateTime dateEnd,
            @RequestParam(required = false) LocalDateTime dateStart) {

        List<Event> events = eventModel.getUserEvents(dateEnd, dateStart, userId);

        return events.stream().map(EventDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public EventDTO registerEvent(@Valid @RequestBody EventDTO eventDTO) {
        if (eventDTO.getDateTime() == null) {
            eventDTO.setDateTime(LocalDateTime.now());
        }

        return objectMapper.convertValue(
                eventModel.addEvent(objectMapper.convertValue(eventDTO, Event.class)),
                EventDTO.class);
    }

    private final EventModel eventModel;
    private final ObjectMapper objectMapper;
}
