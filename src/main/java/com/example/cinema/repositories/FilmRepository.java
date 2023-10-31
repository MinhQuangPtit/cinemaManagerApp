package com.example.cinema.repositories;

import com.example.cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Integer> {
}
