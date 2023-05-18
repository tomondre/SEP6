package com.sep6.backend.repository;

import com.sep6.backend.jpa.TokenJpaRepository;
import com.sep6.backend.security.token.Token;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
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
    public Token save(Token storedToken) throws IllegalArgumentException
    {
        try
        {
            return jpaRepository.save(storedToken);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("A null token cannot be saved");
        }
    }

    @Override
    public List<Token> saveAll(List<Token> validUserTokens) throws IllegalArgumentException
    {
        try
        {
            return jpaRepository.saveAll(validUserTokens);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("There are null tokens in the list");
        }
    }
}
