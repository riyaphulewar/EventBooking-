package com.eventbookingsystem.eventbookingsystem.service;


import com.eventbookingsystem.eventbookingsystem.dto.EventRequest;
import com.eventbookingsystem.eventbookingsystem.entity.Event;
import com.eventbookingsystem.eventbookingsystem.entity.User;
import com.eventbookingsystem.eventbookingsystem.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final NotificationService notificationService;

    public EventService(EventRepository eventRepository,
                        NotificationService notificationService) {
        this.eventRepository = eventRepository;
        this.notificationService = notificationService;
    }

    public Event createEvent(EventRequest request, User organizer) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setAvailableTickets(request.getAvailableTickets());
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, EventRequest request) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setAvailableTickets(request.getAvailableTickets());

        Event updated = eventRepository.save(event);
        notificationService.notifyEventUpdate(updated);
        return updated;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
