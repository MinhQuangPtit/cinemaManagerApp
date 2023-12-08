package com.example.cinema.configurations;


import com.example.cinema.filterRequest.JwtTokenFilter;
import com.example.cinema.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests->{
                    requests.requestMatchers(
                                "user/register",
                                "user/login"
                            ).permitAll()
                            .requestMatchers(GET,"film/**").permitAll()
                            .requestMatchers(POST,"film").hasAnyRole(Role.ADMIN)

                            .requestMatchers(GET,"category/**").permitAll()
                            .requestMatchers(POST,"category").hasAnyRole(Role.ADMIN)
                            .requestMatchers(PUT,"category/**").hasAnyRole(Role.ADMIN)
                            .requestMatchers(DELETE,"category/**").hasAnyRole(Role.ADMIN)

                            .requestMatchers(GET,"service/**").permitAll()
                            .requestMatchers(POST,"service").hasAnyRole(Role.ADMIN)
                            .requestMatchers(PUT,"service/**").hasAnyRole(Role.ADMIN)
                            .requestMatchers(DELETE,"service/**").hasAnyRole(Role.ADMIN)


                            .requestMatchers(GET,"room").hasAnyRole(Role.ADMIN)
                            .requestMatchers(POST,"room").hasAnyRole(Role.ADMIN)
                            .requestMatchers(PUT,"room/**").hasAnyRole(Role.ADMIN)
                            .requestMatchers(DELETE,"room/**").hasAnyRole(Role.ADMIN)


                            .requestMatchers(GET,"ticket**").hasAnyRole(Role.ADMIN,Role.USER)
                            .requestMatchers(POST,"ticket").hasAnyRole(Role.ADMIN,Role.USER)
                            .requestMatchers(PUT,"ticket").hasAnyRole(Role.ADMIN,Role.USER)
                            .requestMatchers(DELETE,"ticket").hasAnyRole(Role.ADMIN,Role.USER)

                            .anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
}
