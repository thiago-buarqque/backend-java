package com.evry.analytics.entity;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Event")
public class Event {
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Transient
    private Long eventId;

    @NotNull
    @Transient
    private String eventType;

    @NotNull
    @Transient
    private String metadata;

    @Transient
    private Date eventDate;

    @NotNull
    @Transient
    private Long userId;
}
