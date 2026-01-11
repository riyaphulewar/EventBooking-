package com.eventbookingsystem.eventbookingsystem.repository;

import com.eventbookingsystem.eventbookingsystem.entity.Event;
import com.eventbookingsystem.eventbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizer(User organizer);
}
