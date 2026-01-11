package com.eventbookingsystem.eventbookingsystem.service;


import com.eventbookingsystem.eventbookingsystem.entity.Booking;
import com.eventbookingsystem.eventbookingsystem.entity.Event;
import com.eventbookingsystem.eventbookingsystem.repository.BookingRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final BookingRepository bookingRepository;

    public NotificationService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Async
    public void bookingConfirmation(Booking booking) {
        System.out.println("📧 Email sent to "
                + booking.getCustomer().getEmail()
                + " for booking " + booking.getId());
    }

    @Async
    public void eventUpdated(Event event) {
        bookingRepository.findByEvent(event).forEach(b ->
                System.out.println("🔔 Notified "
                        + b.getCustomer().getEmail()
                        + " about update to event " + event.getTitle()));
    }
    @Async
    public void notifyEventUpdate(Event event) {
        bookingRepository.findByEvent(event)
                .forEach(b ->
                        System.out.println("🔔 Notifying "
                                + b.getCustomer().getEmail()
                                + " about update to event: "
                                + event.getTitle())
                );
    }

}
