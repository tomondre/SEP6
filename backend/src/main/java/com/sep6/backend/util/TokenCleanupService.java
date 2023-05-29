package com.sep6.backend.util;

import com.sep6.backend.repository.TokenRepository;
import com.sep6.backend.security.token.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenCleanupService
{
    private final TokenRepository tokenRepository;

    @Scheduled(cron = "0 0 0 * * *") // Run at midnight every day
    public void cleanExpiredTokens() {
        log.info("Cleaning expired tokens");
        List<Token> expiredTokens = tokenRepository.getAllExpiredTokens();
        tokenRepository.deleteAll(expiredTokens);
    }
}
