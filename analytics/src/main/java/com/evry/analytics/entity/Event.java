package com.evry.analytics.entity;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table
public class Event {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        Event event = (Event) object;

        return Objects.equals(eventId, event.eventId) &&
                Objects.equals(eventType, event.eventType) &&
                Objects.equals(metadata, event.metadata) &&
                Objects.equals(eventDate, event.eventDate) &&
                Objects.equals(userId, event.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventType, metadata, eventDate, userId);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + eventId +
                ", eventType='" + eventType + '\'' +
                ", metadata='" + metadata + '\'' +
                ", timestamp=" + eventDate +
                ", userId=" + userId +
                '}';
    }

    private Date eventDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long eventId;

    private String eventType;

    private String metadata;

    private Long userId;
}
