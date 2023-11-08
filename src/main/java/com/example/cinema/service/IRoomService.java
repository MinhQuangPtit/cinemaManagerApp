package com.example.cinema.service;

import com.example.cinema.dtos.RoomDTO;
import com.example.cinema.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRoomService {
    Room createRoom(RoomDTO roomDTO);
    Room updateRoom(int id, RoomDTO roomDTO);
    Room getRoomById(int id);
    List<Room> searchNameByName(String keyword);
    Page<Room> getAllRoom(PageRequest pageRequest);
    void deleteRoom(int id);
}
