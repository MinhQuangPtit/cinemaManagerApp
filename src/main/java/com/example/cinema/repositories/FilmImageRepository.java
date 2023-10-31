package com.example.cinema.repositories;

import com.example.cinema.model.FilmImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmImageRepository extends JpaRepository<FilmImage,Integer> {
}
