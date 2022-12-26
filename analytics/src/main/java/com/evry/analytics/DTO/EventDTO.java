package com.evry.analytics.DTO;

import com.evry.analytics.annotations.JSONField;
import com.evry.analytics.annotations.JSONSerializable;
import com.evry.analytics.entity.Event;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@EqualsAndHashCode
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

    @JSONField
    private LocalDateTime eventDate;

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