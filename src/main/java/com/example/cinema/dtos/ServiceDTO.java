package com.example.cinema.dtos;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    @NotEmpty(message = "Service's name cannot be empty")
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 1000000000, message = "Price must be less than or equal to 1,000,000,000")
    private float price;
}
