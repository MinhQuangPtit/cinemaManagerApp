package com.example.cinema.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name",nullable = false)
    private String roleName;

    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}
