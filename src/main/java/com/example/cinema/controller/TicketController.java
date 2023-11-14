package com.example.cinema.controller;


import com.example.cinema.dtos.FilmDTO;
import com.example.cinema.dtos.TicketDTO;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable int id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getTicketsByCustomerId(@PathVariable int customerId){
        return ResponseEntity.ok(ticketService.getTicketsByCustomerId(customerId));
    }

    @PostMapping("")
    public ResponseEntity<?> createTicket(
            @RequestBody TicketDTO ticketDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dữ liệu đầu vào không hợp lệ");
        }
        return ResponseEntity.ok(ticketService.createTicket(ticketDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(
            @PathVariable int id,
            @RequestBody TicketDTO ticketDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dữ lệu đầu vào không hợp lệ");
        }
        return ResponseEntity.ok(ticketService.updateTicket(ticketDTO,id));
    }

}
