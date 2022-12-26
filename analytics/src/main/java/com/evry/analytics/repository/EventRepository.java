package com.evry.analytics.repository;

import com.evry.analytics.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends CustomEventRepository,
        JpaRepository<Event, Long> {

    List<Event> findEventsByEventDateBetweenAndUserId(
            LocalDateTime date1, LocalDateTime date2, Long userId);
}
