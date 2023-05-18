package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.auth.AuthenticationRequest;
import com.sep6.backend.models.auth.AuthenticationResponse;
import com.sep6.backend.models.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;


import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws IllegalArgumentException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws IllegalArgumentException,
            AuthenticationException;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}