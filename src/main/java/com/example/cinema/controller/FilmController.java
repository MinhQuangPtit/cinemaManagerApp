package com.example.cinema.controller;


import com.example.cinema.dtos.FilmDTO;
import com.example.cinema.model.Film;
import com.example.cinema.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/jpa")
    public ResponseEntity<?> practiceJPA(
            @RequestParam String name,
            @RequestParam String description
    ){
        return ResponseEntity.ok(filmService.practiceJPA(name,description));
    }

    @PostMapping("")
    public ResponseEntity<?> createFilm(
            @RequestBody FilmDTO filmDTO,
            BindingResult result
            ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok(filmService.createFilm(filmDTO));
    }

    @PostMapping("/upload/{filmId}")
    public ResponseEntity<?> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @PathVariable int filmId
    ){
        try {
            filmService.uploadFilm(file, filmId);
            return ResponseEntity.ok("Video uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload video: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<Film> searchByKeyWord(
            @RequestParam("keyword") String keyword
    ){

        return filmService.searchByKeyWord(keyword);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllFilm(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest pageRequest = PageRequest.of(page,limit, Sort.by("release_date").descending());
        Page<Film> filmPage =  filmService.getAllFilms(pageRequest);
        List<Film> films = filmPage.getContent();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(
            @PathVariable int id
    ){
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getFimByCategoryId(
            @PathVariable int categoryId
    ){
        return ResponseEntity.ok(filmService.getFilmByCategoryId(categoryId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(
            @PathVariable int id,
            @RequestBody FilmDTO filmDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok(filmService.updateFilm(id,filmDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(
            @PathVariable int id
    ){
        filmService.deleteFilm(id);
        return ResponseEntity.ok("Film deleted.");
    }

}
