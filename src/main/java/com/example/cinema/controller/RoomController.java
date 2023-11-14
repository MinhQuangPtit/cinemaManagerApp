package com.example.cinema.controller;


import com.example.cinema.dtos.RoomDTO;
import com.example.cinema.model.Room;
import com.example.cinema.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @PostMapping("")
    public ResponseEntity<?> createRoom(
            @RequestBody RoomDTO roomDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.ok(roomService.createRoom(roomDTO));
    }

    @GetMapping("")
    public ResponseEntity<?> getALlRoom(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest pageRequest = PageRequest.of(page,limit, Sort.by("name").ascending());
        Page<Room> roomPage = roomService.getAllRoom(pageRequest);
        List<Room> rooms = roomPage.getContent();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(
            @PathVariable int id
    ){
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(
            @PathVariable int id,
            @RequestBody RoomDTO roomDTO
    ){
        return ResponseEntity.ok(roomService.updateRoom(id,roomDTO));
    }
}
