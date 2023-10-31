package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmImageDTO {
    @JsonProperty("image_url")
    private String imageURL;

    @JsonProperty("film_id")
    private int filmId;
}
