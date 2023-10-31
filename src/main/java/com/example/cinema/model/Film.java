package com.example.cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @NotNull
    private float price;

    @Column(name = "url_video",nullable = false)
    private String urlVideo;

    private String thumbnail;

    private String description;


    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;


    @OneToMany(mappedBy = "film")
    private List<FilmImage> filmImages;
}
