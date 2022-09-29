package com.evry.analytics.repository;

import com.evry.analytics.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CustomEventRepository,
        JpaRepository<Event, Long> {

    List<Event> findEventsByEventDateBetweenAndUserId(
            Date date1, Date date2, Long userId);
}
