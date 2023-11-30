package com.example.cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_account")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "user_name")
    private String username;

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

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add((new SimpleGrantedAuthority("ROLE_" + this.getRole().getRoleName().toUpperCase())));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
