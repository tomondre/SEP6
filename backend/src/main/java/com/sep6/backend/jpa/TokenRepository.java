package com.sep6.backend.jpa;

import com.sep6.backend.security.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>
{
    @Query(value = """
      select t from Token t inner join Account acc\s
      on t.user.id = acc.id\s
      where acc.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
