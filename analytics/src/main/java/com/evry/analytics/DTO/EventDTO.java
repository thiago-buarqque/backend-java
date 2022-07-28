package com.evry.analytics.DTO;

import com.evry.analytics.entity.Event;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventDTO {
    public EventDTO() {

    }

    public EventDTO(Event event) {
        this.eventId = event.getEventId();
        this.eventType = event.getEventType();
        this.metadata = event.getMetadata();
        this.eventDate = event.getEventDate();
        this.userId = event.getUserId();
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + eventId +
                ", eventType='" + eventType + '\'' +
                ", metadata='" + metadata + '\'' +
                ", timestamp=" + eventDate +
                ", userId=" + userId +
                '}';
    }

    private Long eventId;
    private String eventType;
    private String metadata;
    private Date eventDate;
    private Long userId;
}
