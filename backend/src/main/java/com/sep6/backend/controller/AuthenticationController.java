package com.sep6.backend.controller;

import com.sep6.backend.models.auth.AuthenticationRequest;
import com.sep6.backend.models.auth.AuthenticationResponse;
import com.sep6.backend.models.auth.RegisterRequest;
import com.sep6.backend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController
{

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            log.info("Registering user: {}", request.getUsername());
            return ResponseEntity.ok(service.register(request));
        } catch (IllegalArgumentException e) {
            log.error("Invalid registration request", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during registration", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            //TODO test if email exists
            log.info("Authenticating user: {}", request.getEmail());
            return ResponseEntity.ok(service.authenticate(request));
        } catch (IllegalArgumentException e) {
            log.error("Invalid authentication request", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (AuthenticationException e) {
            log.warn("Authentication failed for user: {}", request.getEmail(), e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during authentication", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            log.info("Refreshing token");
            service.refreshToken(request, response);
        } catch (NoSuchElementException e) {
            log.warn("Invalid refresh token", e);
            response.sendError(HttpStatus.NOT_FOUND.value(), "The refresh token is not valid");
        } catch (Exception e) {
            log.error("An error occurred while refreshing token", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong, please try again later");
        }
    }
}
