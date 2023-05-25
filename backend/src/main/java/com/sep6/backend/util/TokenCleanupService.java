package com.sep6.backend.util;

import com.sep6.backend.jpa.TokenJpaRepository;
import com.sep6.backend.repository.TokenRepository;
import com.sep6.backend.security.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenCleanupService
{
    private final TokenRepository tokenRepository;

    @Scheduled(cron = "0 0 0 * * *") // Run at midnight every day
    public void cleanExpiredTokens() {
        // Implement the logic to query and delete expired records
        List<Token> expiredTokens = tokenRepository.getAllExpiredTokens();
        tokenRepository.deleteAll(expiredTokens);
    }
}
