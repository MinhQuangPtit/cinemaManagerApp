package com.example.cinema.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    @JsonProperty("user_name")
    @NotEmpty(message = "user_name cannot be empty")
    private String userName;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    @JsonProperty("full_name")
    @NotEmpty(message = "full_name cannot be empty")
    private String fullName;

    private String phone;

    private String address;

    private Date dateOfBirth;

    private int isActive;
}
