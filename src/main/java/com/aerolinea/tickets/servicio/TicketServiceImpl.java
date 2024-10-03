package com.aerolinea.tickets.servicio;

import com.aerolinea.tickets.modelo.Ticket;
import com.aerolinea.tickets.repositorio.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketServiceImpl implements ITicketServices {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket saveTicket(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);

    }

    @Override
    public List<Ticket> listTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findTicketById(Integer idTicket) {
        return ticketRepository.findById(idTicket).orElse(null);
    }

}
