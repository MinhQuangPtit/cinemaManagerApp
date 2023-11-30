package com.example.cinema.repositories;

import com.example.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findTicketByUserId(int userId);
}
