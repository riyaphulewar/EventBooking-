package com.eventbookingsystem.eventbookingsystem.repository;

import com.eventbookingsystem.eventbookingsystem.entity.Booking;
import com.eventbookingsystem.eventbookingsystem.entity.Event;
import com.eventbookingsystem.eventbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(User customer);
    List<Booking> findByEvent(Event event);
}

