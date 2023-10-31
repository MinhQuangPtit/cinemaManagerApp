package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    @NotEmpty(message = "Film's name cannot be empty")
    private String name;

//    @NotEmpty(message = "release_date cannot be empty")
    @JsonProperty("release_date")
    private Date releaseDate;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 1000000000, message = "Price must be less than or equal to 1,000,000,000")
    private float price;

    private String thumbnail;

    private String description;

    @JsonProperty("category_id")
    private int categoryId;
}
