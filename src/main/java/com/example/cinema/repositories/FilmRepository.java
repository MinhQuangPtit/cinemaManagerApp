package com.example.cinema.repositories;

import com.example.cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Integer> {

    List<Film> findByNameContainingAndDescriptionContaining(String name, String description);
}
