package com.example.cinema.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "film_images")
public class FilmImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "image_url")
    private String imageURL;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private Film film;
}
