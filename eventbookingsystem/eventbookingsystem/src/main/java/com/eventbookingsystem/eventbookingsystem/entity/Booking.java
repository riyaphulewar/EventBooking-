package com.eventbookingsystem.eventbookingsystem.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "booking")
@NoArgsConstructor @AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User customer;

    private int ticketCount;
}
