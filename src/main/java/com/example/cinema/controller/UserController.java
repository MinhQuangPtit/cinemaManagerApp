package com.example.cinema.controller;


import com.example.cinema.dtos.UserDTO;
import com.example.cinema.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    ResponseEntity<?> Register(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dữ lệu đầu vào không hợp lệ");
        }

        return ResponseEntity.ok(userService.Register(userDTO));
    }

    @PostMapping("/login")
    ResponseEntity<?> Login(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dữ lệu đầu vào không hợp lệ");
        }

        return ResponseEntity.ok(userService.Login(userDTO.getUserName(),userDTO.getPassword()));
    }

}
