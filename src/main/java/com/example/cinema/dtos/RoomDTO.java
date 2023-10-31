package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    @NotEmpty(message = "name's room cannot be empty")
    private String name;

    @JsonProperty("total_seating")
    private int totalSeating;

    private String description;

    @JsonProperty("room_type_id")
    private int roomTypeId;
}
