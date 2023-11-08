package com.example.cinema.service;

import com.example.cinema.dtos.RoomDTO;
import com.example.cinema.exception.CustomException;
import com.example.cinema.model.Room;
import com.example.cinema.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService{
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    @Override
    public Room createRoom(RoomDTO roomDTO) {
        modelMapper.typeMap(RoomDTO.class,Room.class)
                .addMappings(mapper->mapper.skip(Room::setId));
        Room room = new Room();
        modelMapper.map(roomDTO,room);
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(int id,RoomDTO roomDTO) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isEmpty()) throw new CustomException("Không tồn tại room với id : " + id);
        modelMapper.typeMap(RoomDTO.class,Room.class).addMappings(mapper->mapper.skip(Room::setId));
        Room newRoom = new Room();
        modelMapper.map(roomDTO,newRoom);
        return roomRepository.save(newRoom);
    }

    @Override
    public Room getRoomById(int id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isEmpty()){
            throw new CustomException("Không tồn tại room với id : " + id);
        }
        return room.get();
    }

    @Override
    public List<Room> searchNameByName(String keyword) {
        return null;
    }

    @Override
    public Page<Room> getAllRoom(PageRequest pageRequest) {
        Page<Room>  pageRoom = roomRepository.findAll(pageRequest);
        return pageRoom;
    }

    @Override
    public void deleteRoom(int id) {

    }
}
