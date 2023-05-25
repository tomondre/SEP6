package com.sep6.backend.util;

import com.sep6.backend.repository.TokenRepository;
import com.sep6.backend.security.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TokenCleanupServiceTest
{
    @Mock
    private TokenRepository tokenRepository;

    private TokenCleanupService tokenCleanupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenCleanupService = new TokenCleanupService(tokenRepository);
    }

    @Test
    void cleanExpiredTokens_ShouldDeleteExpiredTokens() {
        // Arrange
        List<Token> expiredTokens = new ArrayList<>();
        expiredTokens.add(new Token());
        expiredTokens.add(new Token());

        when(tokenRepository.getAllExpiredTokens()).thenReturn(expiredTokens);

        // Act
        tokenCleanupService.cleanExpiredTokens();

        // Assert
        verify(tokenRepository, times(1)).deleteAll(expiredTokens);
    }
}