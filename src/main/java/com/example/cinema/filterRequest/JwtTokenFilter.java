package com.example.cinema.filterRequest;

import com.example.cinema.components.JwtTokenUtil;
import com.example.cinema.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            if(isByPassToken(request)){
                filterChain.doFilter(request,response);
                return;
            }
        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"unauthorized");
            return;
        }

        final String token = authHeader.substring(7);
        final String username = jwtTokenUtil.extractUsername(token);

        if(username != null
            && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = (User) userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token,user)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        filterChain.doFilter(request,response);
        }
        catch (Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

    }

    private boolean isByPassToken(@NonNull HttpServletRequest request){
        final List<Pair<String,String>> byPassTokens = Arrays.asList(
                Pair.of("/category","GET"),
                Pair.of("/film","GET"),
                Pair.of("/service","GET"),
                Pair.of("/user/register","POST"),
                Pair.of("/user/login","POST")
        );
        for (Pair<String,String> byPassToken : byPassTokens){
            if(request.getServletPath().contains(byPassToken.getLeft())
                && request.getMethod().equals(byPassToken.getRight()) ){
                return true;
            }
        }
        return false;
    }
}
