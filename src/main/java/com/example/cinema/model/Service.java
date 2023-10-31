package com.example.cinema.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "service")
public class Service {
    @Id
    private int id;

    @NotEmpty
    private String name;

    private String description;

    @NotEmpty
    private float price;
}
