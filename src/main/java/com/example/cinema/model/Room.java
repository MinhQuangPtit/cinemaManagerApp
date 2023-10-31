package com.example.cinema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    @Column(name = "total_seating")
    private int totalSeating;

    private String description;

    @ManyToOne
    @JoinColumn(name = "room_type_id",referencedColumnName = "id")
    private RoomType roomType;
}
