package com.example.cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "room_type")
public class RoomType {
    static final String TYPE_1 = "4D_MAx";
    static final String TYPE_2 = "3D_MAX";
    static final String TYPE_3 = "2D";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false)
    private String type;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
}
