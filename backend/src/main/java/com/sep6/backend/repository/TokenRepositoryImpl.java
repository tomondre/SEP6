package com.sep6.backend.repository;

import com.sep6.backend.jpa.TokenJpaRepository;
import com.sep6.backend.security.token.Token;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class TokenRepositoryImpl implements TokenRepository
{
    private TokenJpaRepository jpaRepository;

    @Override
    public List<Token> findAllValidTokenByUser(Integer id) {
        log.info("Finding all valid tokens for user with ID: {}", id);
        return jpaRepository.findAllValidTokenByUser(id);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        log.info("Finding token by token value: {}", token);
        return jpaRepository.findByToken(token);
    }

    @Override
    public Token save(Token storedToken) throws IllegalArgumentException {
        log.info("Saving token: {}", storedToken);
        try {
            return jpaRepository.save(storedToken);
        } catch (IllegalArgumentException e) {
            log.error("Failed to save token: {}", storedToken, e);
            throw new IllegalArgumentException("A null token cannot be saved");
        }
    }

    @Override
    public List<Token> saveAll(List<Token> validUserTokens) throws IllegalArgumentException {
        log.info("Saving all tokens: {}", validUserTokens);
        try {
            return jpaRepository.saveAll(validUserTokens);
        } catch (IllegalArgumentException e) {
            log.error("Failed to save tokens: {}", validUserTokens, e);
            throw new IllegalArgumentException("There are null tokens in the list");
        }
    }

    @Override
    public List<Token> getAllExpiredTokens()
    {
        log.info("Getting all expired tokens");
        return jpaRepository.findAllExpiredByExpiredTrue();
    }

    @Override
    public void deleteAll(List<Token> expiredTokens)
    {
        log.info("Deleting all expired tokens");
        jpaRepository.deleteAll(expiredTokens);
    }
}
