package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.Role;
import com.sep6.backend.models.auth.AuthenticationRequest;
import com.sep6.backend.models.auth.AuthenticationResponse;
import com.sep6.backend.models.auth.RegisterRequest;
import com.sep6.backend.repository.AccountsRepository;
import com.sep6.backend.repository.TokenRepository;
import com.sep6.backend.security.config.JwtService;
import com.sep6.backend.security.token.Token;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthenticationServiceImplTest
{
    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationService = new AuthenticationServiceImpl(accountsRepository, tokenRepository, passwordEncoder, jwtService, authenticationManager);
    }

    @Test
    void testRegister() {
        // Arrange
        RegisterRequest request = mock(RegisterRequest.class);
        // Set the properties of the request object

        Account savedUser = new Account();
        String jwtToken = "access_token";
        String refreshToken = "refresh_token";
        String encodedPassword = "encoded_password";
        String dateOfBirth = "1999-01-01";
        savedUser.setPassword(encodedPassword);
        savedUser.setDateOfBirth(Date.valueOf(dateOfBirth));
        savedUser.setEnabled(true);
        savedUser.setRole(Role.USER);

        when(request.getDateOfBirth()).thenReturn(dateOfBirth);
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(accountsRepository.save(any(Account.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(Account.class))).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(any(Account.class))).thenReturn(refreshToken);
        // Act
        AuthenticationResponse response = authenticationService.register(request);

        // Assert
        assertEquals(jwtToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());
        verify(accountsRepository, times(1)).save(any(Account.class));
        verify(jwtService, times(1)).generateToken(savedUser);
        verify(jwtService, times(1)).generateRefreshToken(any(Account.class));
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    void testAuthenticate() {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest();
        // Set the properties of the request object

        Account user = new Account();  // Create a mock of the user
        String jwtToken = "access_token";
        String refreshToken = "refresh_token";

        when(accountsRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);

        // Act
        AuthenticationResponse response = authenticationService.authenticate(request);

        // Assert
        assertEquals(jwtToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(accountsRepository, times(1)).findByEmail(request.getEmail());
        verify(jwtService, times(1)).generateToken(user);
        verify(jwtService, times(1)).generateRefreshToken(user);
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    void testRefreshToken() throws IOException
    {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStream(){
            @Override
            public boolean isReady()
            {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener listener)
            {

            }

            @Override
            public void write(int b) throws IOException {
                outputStream.write(b);
            }
        });

        final String authHeader = "Bearer refresh_token";
        final String refreshToken = "refresh_token";
        final String userEmail = "user@example.com";

        Account user = new Account();  // Create a mock of the user
        String accessToken = "access_token";

        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(authHeader);
        when(jwtService.extractUsername(refreshToken)).thenReturn(userEmail);
        when(accountsRepository.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(jwtService.isTokenValid(refreshToken, user)).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn(accessToken);

        // Act
        authenticationService.refreshToken(request, response);

        // Assert
        verify(request, times(1)).getHeader(HttpHeaders.AUTHORIZATION);
        verify(jwtService, times(1)).extractUsername(refreshToken);
        verify(accountsRepository, times(1)).findByEmail(userEmail);
        verify(jwtService, times(1)).isTokenValid(refreshToken, user);
        verify(jwtService, times(1)).generateToken(user);
        verify(response, times(1)).getOutputStream();
    }
}