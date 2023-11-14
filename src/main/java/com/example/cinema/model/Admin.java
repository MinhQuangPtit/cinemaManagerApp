package com.example.cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "user_name")
    private String userName;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(name = "full_name")
    private String fullName;

    private String phone;


    private String address;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "is_active")
    private int isActive;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Ticket> tickets;
}
