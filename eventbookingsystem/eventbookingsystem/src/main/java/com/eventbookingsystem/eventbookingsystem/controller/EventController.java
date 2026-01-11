package com.eventbookingsystem.eventbookingsystem.controller;


import com.eventbookingsystem.eventbookingsystem.dto.EventRequest;
import com.eventbookingsystem.eventbookingsystem.entity.Event;
import com.eventbookingsystem.eventbookingsystem.entity.User;
import com.eventbookingsystem.eventbookingsystem.repository.UserRepository;
import com.eventbookingsystem.eventbookingsystem.service.EventService;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final UserRepository userRepository;

    public EventController(EventService eventService, UserRepository userRepository) {
        this.eventService = eventService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public Event create(@RequestBody EventRequest request) {
        User organizer = userRepository.findByEmail(request.getUserName()).orElseThrow();
        return eventService.createEvent(request, organizer);
    }

    @PutMapping("/update/{id}")
    public Event update(@PathVariable Long id, @RequestBody EventRequest request) {
        return eventService.updateEvent(id, request);
    }

    @GetMapping
    public List<Event> all() {
        return eventService.getAllEvents();
    }
}
