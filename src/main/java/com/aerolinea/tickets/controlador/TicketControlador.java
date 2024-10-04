package com.aerolinea.tickets.controlador;

import com.aerolinea.tickets.modelo.Ticket;
import com.aerolinea.tickets.modelo.TicketType;
import com.aerolinea.tickets.servicio.ITicketServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/tickets")
public class TicketControlador {

    Logger logger = LoggerFactory.getLogger(TicketControlador.class);

    @Autowired
    ITicketServices iTicketServices;

    /**
     * si el usuario elige solo ida, debe estar lleno el departureDate y returnDate no se tiene en cuenta y se pone a nulo
     * tripType debe colocarse a TicketType.ONE_WAY_TRIP
     * si el usuario elige ida y vuelta, deben estar llenos los dos, sino estan llenos los dos, se manda el Bad_request
     * el tripType debe colocarse a TicketType.ROUND_TRIP
     */
    @PostMapping("/generar")
    public ResponseEntity<Ticket> generateTicket(@RequestParam String ticketType, @RequestParam String origin, @RequestParam String destination,
                                                @RequestParam String departureDate, @RequestParam (required = false) String returnDate) {

        LocalDate parsedDepartureDate = LocalDate.parse(departureDate);
        LocalDate parsedReturnDate = LocalDate.parse(returnDate);
        TicketType tripType = null;

        /**
         * Re estructurar bloque de if.
         */
        if("soloida".equals(ticketType)) {
            if(parsedDepartureDate == null) {
                logger.info("parsedDepartureDate = null");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                logger.info("departureDate ok");
                tripType = TicketType.ONE_WAY_TRIP;
            }
        } else if ("idayvuelta".equals(ticketType)) {
            if(parsedDepartureDate==null || parsedReturnDate==null) {
                logger.info("departure or return date are not defined");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                logger.info("idayvuelta: departure and return date are ok");
                tripType = TicketType.ROUND_TRIP;
            }
        }

        Ticket ticket = Ticket.builder()
                .ticketType(tripType)
                .origin(origin)
                .destination(destination)
                .departureDate(LocalDate.parse(departureDate))
                .returnDate(parsedReturnDate)
                .build();

        Ticket savedTicket = iTicketServices.saveTicket(ticket);
        return ResponseEntity.status(201).body(savedTicket);

    }
}
