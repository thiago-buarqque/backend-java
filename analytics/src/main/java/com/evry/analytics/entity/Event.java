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

@Entity
@Getter
@Setter
@Table
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

    @Transient
    private Date eventDate;

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
    private Long userId;
}
