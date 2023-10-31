package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TicketDTO {
    @NotEmpty(message = "film_id cannot be empty")
    @JsonProperty("film_id")
    private int filmId;

    @NotEmpty(message = "room_id cannot be empty")
    @JsonProperty("room_id")
    private int roomId;

    @NotEmpty(message = "admin_id cannot be empty")
    @JsonProperty("admin_id")
    private String adminId;

    @NotEmpty(message = "customer_id cannot be empty")
    @JsonProperty("customer_id")
    private String customerId;

    @NotEmpty(message = "seat_code cannot be empty")
    @JsonProperty("seat_code")
    private String seatCode;


    @Min(value = 0, message = "total_price must be greater than or equal to 0")
    @Max(value = 1000000000, message = "total_price must be less than or equal to 1,000,000,000")
    @JsonProperty("total_price")
    private float totalPrice;

    @JsonProperty("create_at")
    private Date createdAt;

    private LocalDateTime showtime;
}
