package com.sep6.backend.service;

import com.sep6.backend.repository.TokenRepository;
import com.sep6.backend.security.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LogoutServiceTest
{
    private LogoutService logoutService;

    @Mock
    private TokenRepository tokenRepository;

    @Test
    void testLogout() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        logoutService = new LogoutService(tokenRepository);

        String jwt = "your-jwt-token";
        Authentication authentication = new UsernamePasswordAuthenticationToken("user123", null);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(tokenRepository.findByToken(jwt)).thenReturn(Optional.of(mock(Token.class)));

        // Act
        logoutService.logout(request, response, authentication);

        // Assert
        verify(tokenRepository, times(1)).findByToken(jwt);
        verify(tokenRepository, times(1)).save(any(Token.class));
        // Add additional assertions based on your implementation and desired behavior
    }
}