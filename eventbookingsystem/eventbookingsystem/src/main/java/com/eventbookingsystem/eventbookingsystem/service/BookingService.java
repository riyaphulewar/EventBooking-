package com.eventbookingsystem.eventbookingsystem.service;



import com.eventbookingsystem.eventbookingsystem.dto.BookingRequest;
import com.eventbookingsystem.eventbookingsystem.entity.Booking;
import com.eventbookingsystem.eventbookingsystem.entity.Event;
import com.eventbookingsystem.eventbookingsystem.entity.User;
import com.eventbookingsystem.eventbookingsystem.repository.BookingRepository;
import com.eventbookingsystem.eventbookingsystem.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;

    public BookingService(EventRepository eventRepository,
                          BookingRepository bookingRepository,
                          NotificationService notificationService) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
        this.notificationService = notificationService;
    }

    public Booking bookEvent(Long eventId, BookingRequest request, User customer) {
        Event event = eventRepository.findById(eventId).orElseThrow();

        if (event.getAvailableTickets() < request.getTicketCount()) {
            throw new RuntimeException("Not enough tickets available");
        }

        event.setAvailableTickets(event.getAvailableTickets() - request.getTicketCount());

        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setCustomer(customer);
        booking.setTicketCount(request.getTicketCount());

        Booking saved = bookingRepository.save(booking);
        notificationService.bookingConfirmation(saved);
        return saved;
    }
}
