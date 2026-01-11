package com.eventbookingsystem.eventbookingsystem.controller;

import com.eventbookingsystem.eventbookingsystem.dto.BookingRequest;
import com.eventbookingsystem.eventbookingsystem.entity.Booking;
import com.eventbookingsystem.eventbookingsystem.entity.User;
import com.eventbookingsystem.eventbookingsystem.repository.UserRepository;
import com.eventbookingsystem.eventbookingsystem.service.BookingService;
//import org.springframework.security.core.Authentication;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;

    public BookingController(BookingService bookingService,
                             UserRepository userRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @PostMapping("/book/{eventId}")
    public Booking book(@PathVariable Long eventId,
                        @RequestBody BookingRequest request) {
        User customer = userRepository.findByEmail(request.getUserName()).orElseThrow();
        return bookingService.bookEvent(eventId, request, customer);
    }
}
