package com.sep6.backend.security.config;

import com.sep6.backend.repository.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenJpaRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("Filtering request: {}", request.getServletPath());
        if ((!request.getServletPath().matches("/accounts/.*")) &&
            ((!request.getServletPath().matches("/movies/[^/]+/reviews.*")) || request.getMethod().equals("GET")))
        {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("No auth header");
            sendUnauthorizedResponse(response);
            return;
        }
        log.info("Auth header: {}", authHeader);
        jwt = authHeader.substring(7);
        try {
            userEmail = jwtService.extractUsername(jwt);
            log.info("User email: {}", userEmail);
        } catch (ExpiredJwtException e) {
            log.info("Token expired: {}", jwt);
            sendTokenExpiredResponse(response);
            return;
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            log.info("User details: {}", userDetails);
            var isTokenValid = tokenJpaRepository.findByToken(jwt)
                                                 .map(t -> !t.isExpired() && !t.isRevoked())
                                                 .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("User authenticated: {}", userEmail);
            } else {
                sendUnauthorizedResponse(response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Not authorized");
    }

    private void sendTokenExpiredResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token expired");
    }
}