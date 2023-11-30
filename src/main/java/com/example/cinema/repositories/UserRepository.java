package com.example.cinema.repositories;

import com.example.cinema.model.User;
import com.example.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);


    Boolean existsByUsername(String username);
}
