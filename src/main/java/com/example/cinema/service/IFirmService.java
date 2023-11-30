package com.example.cinema.service;

import com.example.cinema.dtos.FilmDTO;
import com.example.cinema.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFirmService {

    Film createFilm(FilmDTO filmDTO);

    void uploadFilm(MultipartFile file, int filmId) throws IOException;

    Page<Film> getAllFilms(PageRequest pageRequest);

    Film getFilmById(int id);

    List<Film> getFilmByCategoryId(int categoryId);

    Film updateFilm(int id, FilmDTO filmDTO);

    List<Film> searchByKeyWord(String keyword);

    void deleteFilm(int id);

    List<Film> practiceJPA(String name, String description);
}
