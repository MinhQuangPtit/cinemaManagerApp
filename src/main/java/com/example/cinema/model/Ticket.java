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

    @Column(name = "total_price")
    @NotEmpty
    @Min(value = 0)
    private float totalPrice;

    @Column(name = "seat_code")
    @NotEmpty
    private String seat_code;

    @Column(name = "created_at")
    @NotEmpty
    private LocalDateTime createdAt;

    @NotEmpty
    private LocalDateTime showtime;

    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "admin_id",referencedColumnName = "id")
    private Admin admin;
}
