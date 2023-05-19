package com.sep6.backend.repository;

import com.sep6.backend.security.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

public interface TokenRepository
{

    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    Token save(Token storedToken) throws IllegalArgumentException;

    List<Token> saveAll(List<Token> validUserTokens) throws IllegalArgumentException;
}
