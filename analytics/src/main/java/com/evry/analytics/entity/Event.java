package com.evry.analytics.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table
public class Event {

    private LocalDateTime eventDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long eventId;

    private String eventType;

    private String metadata;

    private Long userId;

}
