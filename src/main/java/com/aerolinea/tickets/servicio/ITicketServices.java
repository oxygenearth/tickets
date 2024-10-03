package com.aerolinea.tickets.servicio;

import com.aerolinea.tickets.modelo.Ticket;

import java.util.List;

/**
 * interface para implementar ordenes crud
 */
public interface ITicketServices {

    public Ticket saveTicket(Ticket ticket);
    public void deleteTicket(Ticket ticket);
    public List<Ticket> listTickets();
    public Ticket findTicketById(Integer idTicket);

}
