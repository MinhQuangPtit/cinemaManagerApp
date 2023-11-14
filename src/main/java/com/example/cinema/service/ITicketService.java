package com.example.cinema.service;

import com.example.cinema.dtos.TicketDTO;
import com.example.cinema.model.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket createTicket(TicketDTO ticketDTO);
    Ticket getTicketById(int id);
    List<Ticket> getTicketsByCustomerId(int customerId);
    Ticket updateTicket(TicketDTO ticketDTO,int id);
}
