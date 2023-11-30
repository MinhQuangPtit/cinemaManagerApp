package com.example.cinema.service;


import com.example.cinema.components.JwtTokenUtil;
import com.example.cinema.dtos.UserDTO;
import com.example.cinema.exception.CustomException;
import com.example.cinema.model.Role;
import com.example.cinema.model.User;
import com.example.cinema.repositories.RoleRepository;
import com.example.cinema.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public User Register(UserDTO userDTO) {

        if(userRepository.existsByUsername(userDTO.getUserName())){
            throw new CustomException("Username is already exist");
        }

        Optional<Role> role = roleRepository.findById(userDTO.getRoleId());
        if(role.isEmpty()) throw new CustomException("role is not exist");
        User user = new User().builder()
                .username(userDTO.getUserName())
                .fullName(userDTO.getFullName())
                .phone(userDTO.getPhone())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .build();
        String encoderPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setRole(role.get());
        user.setPassword(encoderPassword);
        return userRepository.save(user);
    }

    @Override
    public String Login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new CustomException("Invalid username/password");
        if(!passwordEncoder.matches(password,user.get().getPassword())){
            throw new CustomException("wrong username or password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password, user.get().getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(user.get());
    }
}
