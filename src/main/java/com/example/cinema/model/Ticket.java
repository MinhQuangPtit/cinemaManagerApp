package com.example.cinema.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_price",nullable = false)
    @Min(value = 0)
    private float totalPrice;

    @Column(name = "seat_code",nullable = false)
    private String seatCode;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "showtime",nullable = false)
    private LocalDateTime showtime;

    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
