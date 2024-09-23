package com.skysoft.krd.uber.security;

import com.skysoft.krd.uber.entities.User;
import com.skysoft.krd.uber.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = requestTokenHeader.replace("Bearer ", "");
            Long userID=jwtService.getUserIdFromToken(token);

            if (userID != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                User user= userService.getUserById(userID);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());


                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) //information about user like ip address evice type this is important if you want to take limitation and attack
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            filterChain.doFilter(request, response);

        }catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

}
