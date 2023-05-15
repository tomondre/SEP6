package com.sep6.backend.repository;

import com.sep6.backend.jpa.TokenJpaRepository;
import com.sep6.backend.security.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository
{
    private TokenJpaRepository jpaRepository;

    @Override
    public List<Token> findAllValidTokenByUser(Integer id)
    {
        return jpaRepository.findAllValidTokenByUser(id);
    }

    @Override
    public Optional<Token> findByToken(String token)
    {
        return jpaRepository.findByToken(token);
    }

    @Override
    public Token save(Token storedToken)
    {
        return jpaRepository.save(storedToken);
    }

    @Override
    public List<Token> saveAll(List<Token> validUserTokens)
    {
        return jpaRepository.saveAll(validUserTokens);
    }
}
