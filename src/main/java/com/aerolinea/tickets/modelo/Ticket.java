package com.aerolinea.tickets.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
}
