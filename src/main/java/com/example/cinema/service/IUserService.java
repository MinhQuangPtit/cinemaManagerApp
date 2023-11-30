package com.example.cinema.service;


import com.example.cinema.dtos.UserDTO;
import com.example.cinema.model.User;

public interface IUserService {
    public User Register(UserDTO userDTO);
    public String Login(String username, String password);
}
