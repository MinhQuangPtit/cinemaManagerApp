package com.example.cinema.service;

import com.example.cinema.dtos.FilmDTO;
import com.example.cinema.exception.CustomException;
import com.example.cinema.model.Category;
import com.example.cinema.model.Film;
import com.example.cinema.repositories.CategoryRepository;
import com.example.cinema.repositories.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmService implements IFirmService{
    private final CategoryRepository categoryRepository;
    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;

    @Override
    public Film createFilm(FilmDTO filmDTO) {
        Optional<Category> category = categoryRepository.findById(filmDTO.getCategoryId());
        if(category.isEmpty()){
            throw new CustomException("Không tồn tại category với id : " + filmDTO.getCategoryId());
        }
        modelMapper.typeMap(FilmDTO.class, Film.class).addMappings(mapper->mapper.skip(Film::setId));
        Film film = new Film();
        modelMapper.map(filmDTO,film);
        film.setCategory(category.get());
        return filmRepository.save(film);
    }

    @Override
    public void uploadFilm(MultipartFile file, int filmId) throws IOException {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new CustomException("Film not found with ID: " + filmId));

        // Lưu video vào thư mục lưu trữ
        String videoPath = saveVideo(file);

        // Cập nhật đường dẫn file vào trường urlFilm
        film.setUrlVideo(videoPath);
        filmRepository.save(film);

    }

    private String saveVideo(MultipartFile file) throws IOException {
        // Lưu file vào thư mục lưu trữ (vd: /uploads)
        java.nio.file.Path uploadDir = Paths.get("uploads");
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public Page<Film> getAllFilms(PageRequest pageRequest) {
        Page<Film> listFilm = filmRepository.findAll(pageRequest);
        return listFilm;
    }

    @Override
    public Film getFilmById(int id) {
        Optional<Film> film = filmRepository.findById(id);
        if(film.isEmpty()){
            throw new CustomException("Không tồn tại film với id : " + id);
        }
        return film.get();
    }

    @Override
    public List<Film> getFilmByCategoryId(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new CustomException("Không tồn tại category với id : " + categoryId);
        }
        List<Film> films = category.get().getFilms();
        return films;
    }

    @Override
    public Film updateFilm(int id, FilmDTO filmDTO) {
        Optional<Film> film = filmRepository.findById(id);
        if(film.isEmpty()){
            throw new CustomException("Không tồn tại film với id : " + id);
        }
        Optional<Category> category = categoryRepository.findById(filmDTO.getCategoryId());
        if(category.isEmpty()){
            throw new CustomException("Không tồn tại category với id : " + filmDTO.getCategoryId());
        }
        Film newFilm = new Film();
        modelMapper.typeMap(FilmDTO.class,Film.class).addMappings(mapper->mapper.skip(Film::setId));
        modelMapper.map(filmDTO,newFilm);
        newFilm.setId(id);
        return filmRepository.save(newFilm);
    }

    @Override
    public List<Film> searchByKeyWord(String keyword) {
        List<Film> films = filmRepository.findAll();
        List<Film> results = new ArrayList<>();
        for(Film film : films){
            if (film.getName().toLowerCase().contains(keyword.toLowerCase())) results.add(film);
        }
        return results;
    }

    @Override
    public void deleteFilm(int id) {
        Optional<Film> film = filmRepository.findById(id);
        if(film.isEmpty()){
            throw new CustomException("Không tồn tại film với id : " + id);
        }
        filmRepository.deleteById(id);
    }

    @Override
    public List<Film> practiceJPA(String name, String description) {
        return filmRepository.findByNameContainingAndDescriptionContaining(name,description);
    }
}
