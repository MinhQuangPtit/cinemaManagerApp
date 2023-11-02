package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "Customer's name cannot be empty")

    private String name;

    private String phone;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    private String z;
}
