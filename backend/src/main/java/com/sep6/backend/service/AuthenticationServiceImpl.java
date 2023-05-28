package com.sep6.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.Role;
import com.sep6.backend.models.auth.AuthenticationRequest;
import com.sep6.backend.models.auth.AuthenticationResponse;
import com.sep6.backend.models.auth.RegisterRequest;
import com.sep6.backend.repository.AccountsRepository;
import com.sep6.backend.repository.TokenRepository;
import com.sep6.backend.security.config.JwtService;
import com.sep6.backend.security.token.Token;
import com.sep6.backend.security.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService
{
    private final AccountsRepository accountsRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request)
    {
        log.info("Registering new user: {}", request.getUsername());

        Date dateOfBirth;
        try {
            dateOfBirth = Date.valueOf(request.getDateOfBirth());
        } catch (IllegalArgumentException e) {
            log.error("Invalid date format", e);
            throw new IllegalArgumentException("Invalid date format");
        }

        var user = Account.builder()
                          .name(request.getName())
                          .username(request.getUsername())
                          .password(passwordEncoder.encode(request.getPassword()))
                          .email(request.getEmail())
                          .country(request.getCountry())
                          .profilePictureUrl(request.getProfilePictureUrl())
                          .dateOfBirth(dateOfBirth)
                          .gender(request.getGender())
                          .role(Role.USER)
                          .isEnabled(true)
                          .build();
        var savedUser = accountsRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                                     .accessToken(jwtToken)
                                     .refreshToken(refreshToken)
                                     .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws IllegalArgumentException,
            AuthenticationException
    {
        log.info("Authenticating user: {}", request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = accountsRepository.findByEmail(request.getEmail())
                                     .or(() -> accountsRepository.findByUsername(request.getEmail()))
                                     .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                                     .accessToken(jwtToken)
                                     .refreshToken(refreshToken)
                                     .build();
    }

    private void saveUserToken(Account user, String jwtToken) {
        log.info("Saving token for user: {}", user.getEmail());
        var token =
                Token.builder()
                     .user(user)
                     .token(jwtToken)
                     .tokenType(TokenType.BEARER)
                     .expired(false)
                     .revoked(false)
                     .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Account user) {
        log.info("Revoking all tokens for user: {}", user.getEmail());
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException
    {
        log.info("Refreshing access token");
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.accountsRepository.findByUsername(userEmail)
                                              .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                                                         .accessToken(accessToken)
                                                         .refreshToken(refreshToken)
                                                         .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
