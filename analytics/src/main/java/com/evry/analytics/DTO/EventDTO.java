package com.evry.analytics.DTO;

import com.evry.analytics.annotations.JSONField;
import com.evry.analytics.annotations.JSONSerializable;
import com.evry.analytics.entity.Event;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Getter
@JSONSerializable
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
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        EventDTO eventDTO = (EventDTO) object;

        return Objects.equals(eventId, eventDTO.eventId) &&
               Objects.equals(eventType, eventDTO.eventType) &&
               Objects.equals(metadata, eventDTO.metadata) &&
               Objects.equals(eventDate, eventDTO.eventDate) &&
               Objects.equals(userId, eventDTO.userId);
    }

    @JSONField
    @javax.validation.constraints.NotNull(
            message = "Event date must be defined."
    )
    private Date eventDate;

    @JSONField
    private Long eventId;

    @JSONField
    @NotEmpty(message = "Invalid event type.")
    private String eventType;

    @JSONField
    @NotEmpty(message = "Metadata can not be empty.")
    private String metadata;

    @JSONField
    @NotNull
    private Long userId;

}