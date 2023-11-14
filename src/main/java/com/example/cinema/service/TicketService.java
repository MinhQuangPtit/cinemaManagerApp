package com.example.cinema.service;


import com.example.cinema.dtos.TicketDTO;
import com.example.cinema.exception.CustomException;
import com.example.cinema.model.*;
import com.example.cinema.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService{
    private final TicketRepository ticketRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final FilmRepository filmRepository;

    @Override
    public Ticket createTicket(TicketDTO ticketDTO) {
        Optional<Admin> admin = adminRepository.findById(ticketDTO.getAdminId());
        if(admin.isEmpty()){
            throw new CustomException("Admin not found with id " + ticketDTO.getAdminId());
        }

        Optional<Customer> customer = customerRepository.findById(ticketDTO.getCustomerId());
        if(customer.isEmpty()){
            throw new CustomException("Customer not found with id " + ticketDTO.getCustomerId());
        }

        Optional<Room> room = roomRepository.findById(ticketDTO.getRoomId());
        if(room.isEmpty()){
            throw new CustomException("Room not found with id " + ticketDTO.getRoomId());
        }

        Optional<Film> film = filmRepository.findById(ticketDTO.getFilmId());
        if(film.isEmpty()){
            throw new CustomException("Film not found with id " + ticketDTO.getFilmId());
        }

        Ticket newTicket = new Ticket().builder()
                .admin(admin.get())
                .seatCode(ticketDTO.getSeatCode())
                .film(film.get())
                .room(room.get())
                .customer(customer.get())
                .showtime(ticketDTO.getShowtime())
                .totalPrice(ticketDTO.getTotalPrice())
                .createdAt(LocalDateTime.now())
                .build();
        return ticketRepository.save(newTicket);
    }

    @Override
    public Ticket getTicketById(int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty()){
            throw new CustomException("Ticket not found with id " + id);
        }

        return ticket.get();
    }

    @Override
    public List<Ticket> getTicketsByCustomerId(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty()) throw new CustomException("Customer not found with id " + customerId);
        List<Ticket> tickets = customer.get().getTickets();
        return tickets;
    }

    @Override
    public Ticket updateTicket(TicketDTO ticketDTO, int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty()){
            throw new CustomException("Ticket not found with id " + id);
        }

        Optional<Admin> admin = adminRepository.findById(ticketDTO.getAdminId());
        if(admin.isEmpty()){
            throw new CustomException("Admin not found with id " + ticketDTO.getAdminId());
        }

        Optional<Customer> customer = customerRepository.findById(ticketDTO.getCustomerId());
        if(customer.isEmpty()){
            throw new CustomException("Customer not found with id " + ticketDTO.getCustomerId());
        }

        Optional<Room> room = roomRepository.findById(ticketDTO.getRoomId());
        if(room.isEmpty()){
            throw new CustomException("Room not found with id " + ticketDTO.getRoomId());
        }

        Optional<Film> film = filmRepository.findById(ticketDTO.getFilmId());
        if(film.isEmpty()){
            throw new CustomException("Film not found with id " + ticketDTO.getFilmId());
        }

        Ticket newTicket = new Ticket().builder()
                .id(id)
                .admin(admin.get())
                .seatCode(ticketDTO.getSeatCode())
                .film(film.get())
                .room(room.get())
                .customer(customer.get())
                .showtime(ticketDTO.getShowtime())
                .totalPrice(ticketDTO.getTotalPrice())
                .createdAt(LocalDateTime.now())
                .build();
        return ticketRepository.save(newTicket);
    }
}
